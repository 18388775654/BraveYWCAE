package com.yang.cae.modules.controller;



import com.yang.cae.modules.dto.UserResetDTOO;
import com.yang.cae.modules.dto.UserRegisterDTO;
import com.yang.cae.modules.entity.NameMajor;
import com.yang.cae.modules.entity.NameProfession;
import com.yang.cae.modules.mapper.jpa.NameMajorJpa;
import com.yang.cae.modules.mapper.jpa.NameProfessionJpa;
import com.yang.cae.modules.service.LoginService;


import com.yang.cae.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/userLogin")
@Api(tags = {"用户登录"})
@Slf4j
public class LoginController {
    @Resource
    private LoginService loginService;

    @Resource
    private NameMajorJpa nameMajorJpa;
    @Resource
    private NameProfessionJpa nameProfessionJpa;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "登录")
    @GetMapping(value = "/login")
    public Result<?> login( @ModelAttribute UserResetDTOO userDTO){
        String token = loginService.login(userDTO);
        System.out.println(userDTO);
        if (token != null ) {
            return Result.ok(token);
        }else {
            return Result.error("密码错误！");
        }
    }

    @ApiOperation(value = "自动登录")
    @GetMapping(value = "/autoLogin")
    public Result<?> autoLogin(@RequestHeader("token")String token){
        if (loginService.autoLogin(token)){
            return Result.ok("自动");
        }else {
            return  Result.error("您的身份已过期，请重新登录！");
        }

    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody UserRegisterDTO registerDTO){
        String msg = loginService.register(registerDTO);
        if (msg != null){
            return Result.ok(msg);
        }else {
            return Result.error("注册失败");
        }
    }

    @ApiOperation(value = "密码重置")
    @PostMapping(value = "/reset")
    public Result<?> reset(@RequestBody UserResetDTOO userDTO){
        String msg = loginService.reset(userDTO);
        if (msg != null){
            return Result.ok(msg);
        }else {
            return Result.error("重置密码失败");
        }

    }

    @ApiOperation(value = "邮箱验证码")
    @GetMapping(value = "/getAuthCode")
    public Result<?> getAuthCode(@RequestParam String email){
        String msg = loginService.getAuthCode(email);
        if (msg != null){
            return Result.ok(msg);
        }else {
            return Result.error("验证验发送失败，请确认后重试");
        }

    }


    @ApiOperation(value = "获取专业")
    @GetMapping(value = "/getMajor")
    public Result<?> getMajor(){
        List<NameMajor> all = nameMajorJpa.findAll();
        return Result.ok(all);
    }

    @ApiOperation(value = "获取职业")
    @GetMapping(value = "/getProfession")
    public Result<?> getProfession(){
        List<NameProfession> all = nameProfessionJpa.findAll();
        return Result.ok(all);
    }

//    @ApiOperation(value = "图片验证码")
//    @PostMapping(value = "/picAuthCode")
//    public Result<?> picAuthCode(String IP){
//        StringBuilder str = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < 4; i++) {
//            str.append(random.nextInt(10));
//        }
//        stringRedisTemplate.opsForValue().set(IP+AUTH_CODE,str.toString(),PIC_EXPIRE_TIME,TimeUnit.MINUTES);
//        return Result.ok(str.toString());
//    }
}
