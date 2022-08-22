package com.yang.cae.modules.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "advertise")
public class Advertise implements Serializable {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "id", strategy = "com.yang.cae.util.GenerationType.GenerationTypeUtil")
    @GeneratedValue(generator = "id")
    private String id;
    /**
     * 图片url
     */
    @Column(name = "image_url")
    private String imageUrl;
    /**
     * 广告url
     */
    @Column(name = "advertise_url")
    private String advertiseUrl;
}
