package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 职业信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "name_profession")
public class NameProfession implements Serializable {
    private static final long serialVersionUID =  1L;
    /**
     * 职业信息id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil" )
    @GeneratedValue(generator = "id")
    private String Id;
    /**
     * 职业名称
     */
    @Column(name = "profession_name")
    private String professionName;
    /**
     * 职业分类
     */
    @Column(name = "classification")
    private String classification;
}
