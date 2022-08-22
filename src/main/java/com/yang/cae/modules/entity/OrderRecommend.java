package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 推荐表
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "order_recommend")
public class OrderRecommend implements Serializable {
    private static final long serialVersionUID =  1L;
    /**
     * 用户基本信息id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil" )
    @GeneratedValue(generator = "id")
    private String Id;

    /**
     * 已推荐id
     */
    @Column(name = "name_id")
    private String nameId;

    /**
     * 已推荐名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 推荐信息id
     */
    @Column(name = "message_id")
    private String messageId;

    /**
     * 推荐信息名称
     */
    @Column(name = "message_name")
    private String messageName;

    /**
     * 是否移除
     */
    @Column(name = "is_deleted")
    private String isDeleted;


    /**
     * 推荐信息标志
     */
    @Column(name = "flag")
    private String flag;
}
