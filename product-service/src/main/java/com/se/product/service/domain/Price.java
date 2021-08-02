package com.se.product.service.domain;

import com.se.product.service.domain.audit.DateAudit;
import com.se.product.service.domain.converters.CurrencyConverter;
import com.se.product.service.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity

@Table(indexes = {
        @Index(name = "uniqueMulitIndex", columnList = "currency_type, cost", unique = true)
})
public class Price extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_type")
    @Convert(converter = CurrencyConverter.class)
    private CurrencyType currencyType;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;
}
