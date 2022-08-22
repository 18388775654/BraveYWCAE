package com.yang.cae.modules.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 专业信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "name_major")
public class NameMajor implements Serializable {
    private static final long serialVersionUID =  1L;
    /**
     * 专业信息表id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil" )
    @GeneratedValue(generator = "id")
    private String Id;

    /**
     * 专业名称
     */
    @Column(name = "major_name")
    private String majorName;
    /**
     * 专业分类
     */
    @Column(name = "classification")
    private String classification;
}
