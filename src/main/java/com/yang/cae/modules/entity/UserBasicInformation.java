package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_basic_information")
public class UserBasicInformation implements Serializable {
    private static final long serialVersionUID =  1L;
    /**
     * 用户基本信息id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil" )
    @GeneratedValue(generator = "id")
    private String id;
    /**
     * 用户基本信息昵称
     */
    @Column(name = "nick_name")
    private String nickName;
    /**
     * 创建见时间
     */
    @Column(name = "create_time")
    private String createTime;
    /**
     * 用户基本信息手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * 用户基本信息邮箱
     */
    @Column(name = "email")
    private String email;
    /**
     * 用户基本信息职业id
     */
    @Column(name = "profession_id")
    private String professionId;
    /**
     * 用户基本信息职业
     */
    @Column(name = "profession")
    private String profession;
    /**
     * 用户基本信息专业id
     */
    @Column(name = "major_id")
    private String majorId;
    /**
     * 用户基本信息专业
     */
    @Column(name = "major")
    private String major;
    /**
     * 用户基本信息地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 用户基本信息是否删除
     */
    @Column(name = "is_deleted")
    private String  isDeleted;
    /**
     * 用户基本信息更改时间
     */
    @Column(name = "update_time")
    private String updateTime;
}
