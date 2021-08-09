package com.se.product.service.domain;

import com.se.product.service.domain.audit.DateAudit;
import com.se.product.service.validation.annotation.NullOrNotBlank;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)


@Entity
@Table(name = "product")
public class Product  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Product name can not be blank")
    private String name;

    @OneToMany(mappedBy = "product"
            ,fetch = FetchType.LAZY )
    private Set<Price> prices = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="product_category",
            joinColumns=@JoinColumn (name="product_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();



    public void addCategory(Category category) {
        if(this.categories==null){
            this.categories  = new HashSet<>();
        }
        this.categories.add(category);;
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    public void addPrice(Price price) {
        if(prices  ==null){
            prices = new HashSet<>();
        }
        prices.add(price);
        price.setProduct(this);
    }

    public void removePrice(Price comment) {
        prices.remove(comment);
        comment.setProduct(null);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}
