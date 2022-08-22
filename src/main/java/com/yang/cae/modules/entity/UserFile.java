package com.yang.cae.modules.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_file")
public class UserFile implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户文件id
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil" )
    @GeneratedValue(generator = "id")
    private String Id;
    /**
     * 用户文件id
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 用户文件名称
     */
    @Column(name = "file_name")
    private String fileName;
    /**
     * 用户文件
     */
    @Column(name = "file")
    private String file;

}
