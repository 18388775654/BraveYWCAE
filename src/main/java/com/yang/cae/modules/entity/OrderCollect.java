package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 收藏表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "order_collect")
public class OrderCollect implements Serializable {
    private static final long serialVersionUID =  1L;
    /**
     * 收藏id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil" )
    @GeneratedValue(generator = "id")
    private String Id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 收藏信息id
     */
    @Column(name = "collect_message_id")
    private String collectMessageId;

    /**
     * 收藏信息名称
     */
    @Column(name = "collect_message_name")
    private String collectMessageName;

    /**
     * 是否移除
     */
    @Column(name = "is_deleted")
    private String isDeleted;

    /**
     * 信息标志
     */
    @Column(name = "flag")
    private String flag;

    /**
     * 信息标志
     */
    @Column(name = "collect_time")
    private String collectTime;

}
