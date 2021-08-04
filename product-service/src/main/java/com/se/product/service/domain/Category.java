package com.se.product.service.domain;

import com.se.product.service.validation.annotation.NullOrNotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Category name can not be blank")
    private String name;

    @Column(unique = true,name = "code")
    @NullOrNotBlank(message = "Category code can not be blank")
    private String code;

    @Column(unique = true)
    private Long baseCategory;

    // TODO: move to many-to-many
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

}
