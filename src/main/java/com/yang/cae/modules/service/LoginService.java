package com.yang.cae.modules.service;

import com.yang.cae.modules.dto.UserResetDTOO;
import com.yang.cae.modules.dto.UserRegisterDTO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    /**
     * 用户登录
     * @param userName
     * @param password
     * @return String
     */

    public String login(UserResetDTOO userDTO);

    /**
     * 自动登录
     * @param token
     * @return Boolean
     */
    public Boolean autoLogin(String token);

    /**
     * 用户注册
     * @param information
     * @return
     */
    public String register(UserRegisterDTO registerDTO);

    /**
     * 重置密码
     * @param user
     * @return
     */
    public String reset(UserResetDTOO userDTO);

    public String getAuthCode(String email);


}
