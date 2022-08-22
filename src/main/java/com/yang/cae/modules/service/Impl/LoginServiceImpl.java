package com.yang.cae.modules.service.Impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yang.cae.modules.dto.UserResetDTOO;
import com.yang.cae.modules.dto.UserRegisterDTO;
import com.yang.cae.modules.entity.UserBasicInformation;
import com.yang.cae.modules.entity.UserLoginInformation;
import com.yang.cae.modules.mapper.jpa.UserBasicInformationJpa;
import com.yang.cae.modules.mapper.jpa.UserLoginInformationJpa;
import com.yang.cae.modules.service.LoginService;
import com.yang.cae.util.tokenUtil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.yang.cae.util.SureValue.*;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserLoginInformationJpa userLoginInformationJpa;
    @Autowired
    private UserBasicInformationJpa userBasicInformationJpa;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MailService mailService;

    @Override
    public String login(UserResetDTOO userDTO) {
        String userName = userDTO.getUserName();
        String password = userDTO.getPassword();
        String msg = null;
        final UserLoginInformation user = userLoginInformationJpa.getByEmailAndPassword(userName, password);
        if (user == null){
            return msg;
        }
        if (stringRedisTemplate.hasKey(user.getId() + _TOKEN)){
            msg = String.valueOf(stringRedisTemplate.opsForValue().get(user.getId() + _TOKEN));
        }else {
            String token = TokenUtil.sign(user);
            //将生成的token存入redis中便于调用
            stringRedisTemplate.opsForValue().set(user.getId()+_TOKEN,token,TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
            msg = token;
        }
        return msg;
    }

    @Override
    public Boolean autoLogin(String token) {
        DecodedJWT jwt = TokenUtil.verify(token);
        if(jwt == null){
            return false;
        }
        String id = jwt.getClaim("id").asString();
        if (stringRedisTemplate.hasKey(id+_TOKEN)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String register(UserRegisterDTO registerDTO) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String msg = null;
        String email = registerDTO.getEmail();
        String phoneNumber = registerDTO.getPhoneNumber();
        if(userBasicInformationJpa.existsByEmail(email)){
            return "邮箱已被注册";
        }
        else if (userBasicInformationJpa.existsByPhoneNumber(phoneNumber)){
            return "手机号已被注册";
        }else {
            UserLoginInformation userLoginInformation = new UserLoginInformation();
            userLoginInformation.setPassword(registerDTO.getPassword());
            userLoginInformation.setEmail(email);
            userLoginInformation.setNickName(registerDTO.getNickName());
            userLoginInformation.setPhoneNumber(phoneNumber);
            userLoginInformationJpa.save(userLoginInformation);

            UserBasicInformation userBasicInformation = new UserBasicInformation();
            userBasicInformation.setNickName(registerDTO.getNickName());
            userBasicInformation.setCreateTime(dateFormat.format(new Date()));
            userBasicInformation.setPhoneNumber(phoneNumber);
            userBasicInformation.setAddress(registerDTO.getAddress());
            userBasicInformation.setEmail(email);
            userBasicInformation.setMajor(registerDTO.getMajor());
            userBasicInformation.setMajorId(registerDTO.getMajorId());
            userBasicInformation.setProfession(registerDTO.getProfession());
            userBasicInformation.setProfessionId(registerDTO.getProfessionId());
            //userBasicInformation.setUpdateTime(new Date());
            userBasicInformation.setIsDeleted("1");
            userBasicInformationJpa.save(userBasicInformation);

            if (userBasicInformationJpa.existsByEmail(email)
                    && userBasicInformationJpa.existsByEmail(email)){
                msg = "注册成功";
            }
        }
        return msg;
    }

    @Override
    public String reset(UserResetDTOO userDTO) {
        String userName = userDTO.getUserName();
        String msg = null;
        if (!userLoginInformationJpa.existsByEmail(userName)){
            return "邮箱未注册";
        }
        if (stringRedisTemplate.hasKey(userName)){
            boolean isAuth = stringRedisTemplate.opsForValue().get(userName).equals(userDTO.getAuthCode());
            if (isAuth){
                UserLoginInformation userLoginInformation = userLoginInformationJpa.getByEmail(userName);
                userLoginInformation.setPassword(userDTO.getPassword());
                userLoginInformationJpa.save(userLoginInformation);
                msg = "密码重置成功";
            }else {
                msg = "验证码错误";
            }
        }
        return msg;

    }

    @Override
    public String getAuthCode(String email) {
        String msg = null;
        if (!userLoginInformationJpa.existsByEmail(email)){
            return "邮箱未注册";
        }
        else if (stringRedisTemplate.hasKey(email)) {
            return "请注意查看邮箱！请勿频繁获取！请" + stringRedisTemplate.getExpire(email) + "秒后重试！";
        }else {
            String authCode = mailService.sendVerifyMail(email);
            stringRedisTemplate.opsForValue().set(email,authCode, EMAIL_EXPIRE_TIME, TimeUnit.MINUTES);
            if (authCode != null){
                return "验证验发送成功，请查看邮箱";
            }
        }
        return msg;
    }
}
