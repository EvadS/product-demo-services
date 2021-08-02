package com.se.product.service.domain;

import com.se.product.service.validation.annotation.NullOrNotBlank;

import javax.persistence.*;

// TODO: move to lombook
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Category name can not be blank")
    private String name;

    @Column(unique = true)
    @NullOrNotBlank(message = "Category code can not be blank")
    private String code;


    @Column(unique = true)
    private Long baseCategory;

    // TODO: move to many-to-many
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getBaseCategory() {
        return baseCategory;
    }

    public void setBaseCategory(Long baseCategory) {
        this.baseCategory = baseCategory;
    }
}
