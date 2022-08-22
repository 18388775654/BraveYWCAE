package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 考试信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "message_exam")
public class MessageExam implements Serializable {
    private static final long serialVersionUID =  1L;

    /**
     * 考试信息id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id"
            ,strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil")
    @GeneratedValue(generator = "id")
    private String id;

    /**
     * 考试名称
     */
    @Column(name = "exam_name")
    private String examName;

    /**
     * 考试时长
     */
    @Column(name = "exam_time")
    private String examTime;

    /**
     * 开始报名时间
     */
    @Column(name = "start_registration_time")
    private String startRegistrationTime;

    /**
     * 报名截止时间
     */
    @Column(name = "end_registration_time")
    private String endRegistrationTime;

    /**
     * 报名方式
     */
    @Column(name = "registration_way")
    private String registrationWay;

    /**
     * 报名地址
     */
    @Column(name = "registration_address")
    private String registrationAddress;

    /**
     * 报名所需材料
     */
    @Column(name = "registration_prepare_material")
    private String registrationPrepareMaterial;

    /**
     * 开始考试时间
     */
    @Column(name = "start_exam_time")
    private String startExamTime;

    /**
     * 考试结束时间
     */
    @Column(name = "end_exam_time")
    private String endExamTime;

    /**
     * 考试地点
     */
    @Column(name = "exam_address")
    private String examAddress;

    /**
     * 考试材料
     */
    @Column(name = "prepare_material")
    private String prepareMaterial;

    /**
     * 备考信息
     */
    @Column(name = "exam_preparation_message")
    private String examPreparationMessage;

    /**
     * 性价比
     */
    @Column(name = "cost_effective")
    private String costEffective;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;


    @Column(name = "recommend_major")
    private String recommendMajor;

    @Column(name = "recommend_profession")
    private String recommendProfession;

}
