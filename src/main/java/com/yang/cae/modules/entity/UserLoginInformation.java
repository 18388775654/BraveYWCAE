package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_login_information")
public class UserLoginInformation implements Serializable {

    private static final long serialVersionUID =  1L;

    /**
     * 用户登录id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil")
    @GeneratedValue(generator = "id")
    private String Id;

    /**
     * 用户登录手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 用户登录邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 用户登录密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

}
