package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_search_record")
public class UserSearchRecord {
    private static final long serialVersionUID =  1L;

    /**
     * 用户搜索id
     */
    @javax.persistence.Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil")
    @GeneratedValue(generator = "id")
    private String Id;


    /**
     * 用户登录手机号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户登录手机号
     */
    @Column(name = "search")
    private String search;
    /**
     * 用户登录手机号
     */
    @Column(name = "search_time")
    private String searchTime;

    /**
     * 是否移除
     */
    @Column(name = "is_deleted")
    private String isDeleted;

}
