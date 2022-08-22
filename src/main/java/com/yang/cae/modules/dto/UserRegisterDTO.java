package com.yang.cae.modules.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserRegisterDTO implements Serializable {
    private static final long serialVersionUID =  1L;

    /**
     * 用户基本信息昵称
     */
    private String nickName;
    /**
     * 用户基本信息手机号
     */
    private String phoneNumber;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户基本信息职业id
     */
    private String professionId;
    /**
     * 用户基本信息职业
     */
    private String profession;
    /**
     * 用户基本信息专业id
     */
    private String majorId;
    /**
     * 用户基本信息专业
     */
    private String major;
    /**
     * 用户基本信息地址
     */
    private String address;
    /**
     * 密码
     */
    private String  password;

}
