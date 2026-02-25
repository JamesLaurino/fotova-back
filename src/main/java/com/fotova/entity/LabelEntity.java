package com.fotova.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LabelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descriptionEn") // corespond à la description du product_entity
    private String descriptionEn;
    @Column(name = "descriptionRu")
    private String descriptionRu;
    @Column(name = "descriptionFr")
    private String descriptionFr;

    @Column(name = "titleEn") // correspond au "name" du product_entity
    private String titleEn;
    @Column(name = "titleRu")
    private String titleRu;
    @Column(name = "titleFr")
    private String titleFr;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", unique = true)
    private ProductEntity product;
}
