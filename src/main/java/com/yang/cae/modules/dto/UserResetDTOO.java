package com.yang.cae.modules.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResetDTOO implements Serializable {
    /**
     * 用户验证码  5分钟
     */
    private String authCode;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
}
