package com.se.product.service.domain;

import com.se.product.service.validation.annotation.NullOrNotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NullOrNotBlank(message = "Product name can not be blank")
    private String name;

    @OneToMany(mappedBy = "product"
            ,fetch = FetchType.LAZY )
    private Set<Price> prices = new HashSet<Price>();

    public void addChild(Price price) {
        prices.add(price);
        price.setProduct(this);
    }

    public void removeChild(Price comment) {
        prices.remove(comment);
        comment.setProduct(null);
    }
}
