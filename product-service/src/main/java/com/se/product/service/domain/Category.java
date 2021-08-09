package com.se.product.service.domain;

import com.se.product.service.validation.annotation.NullOrNotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name="product_category",
            joinColumns=@JoinColumn(name="category_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    private Set<Product> products;

//    // TODO: move to many-to-many
//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = true)
//    private Product product;


    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", baseCategory=" + baseCategory +
                '}';
    }
}
