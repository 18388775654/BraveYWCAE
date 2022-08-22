package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "message_work")
public class MessageWork implements Serializable {
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
     * 办事名称
     */
    @Column(name = "work_name")
    private String workName;


    /**
     * 办事流程
     */
    @Column(name = "process")
    private String process;


    /**
     * 所需材料
     */
    @Column(name = "materials_needed")
    private String materialsNeeded;

    /**
     * 部门
     */
    @Column(name = "department")
    private String department;


    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 信息地址
     */
    @Column(name = "address")
    private String address;
}
