package com.se.product.service.domain.specification;

import com.se.product.service.domain.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ProductSpecification extends SearchSpecification<Product, ProductSearch> {

    @Override
    public Specification<Product> getFilter(ProductSearch request) {
        return null;
    }

    // TODO: sample
    /*

    private final WalletRepo walletRepo;

    public TransactionSpecification(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    @Override
    public Specification<Transaction> getFilter(@Valid  TransactionSearch request) {
        return (root, query, cb) -> {
            query.distinct(true); //Important because of the join in the addressAttribute specifications

            Specification<Transaction> walletFrom = walletFromAttributeEqual("walletFrom", request.getUserId(), request.getPaymentCurrency());
            Specification<Transaction> walletTo = walletFromAttributeEqual("walletTo", request.getUserId(), request.getPaymentCurrency());

            return where(
                    dateGreaterThenSpec("createdAt", request.getDateFrom())
                    .and(dateLessSpec("createdAt", request.getDateTo()))
                            .and((walletFrom).or(walletTo))

            ).toPredicate(root, query, cb);
        };
    }


    private Specification<Transaction> paymentSystemTypeContains(String attribute, PaymentCurrency paymentCurrency) {
        return (root, query, cb) -> {
            if (paymentCurrency == null) {
                return null;
            }
            return cb.equal(root.get(attribute), paymentCurrency);
        };
    }

    private Specification<Transaction> dateGreaterThenSpec(String attribute, long startedPeriod) {
        return dateGreaterThanOrEqualTo(attribute, startedPeriod);
    }

    private Specification<Transaction> dateLessSpec(String attribute, long endPeriod) {
        return dateLessThanOrEqualTo(attribute, endPeriod);
    }

    private Specification<Transaction> walletFromAttributeEqual(final String attribute, long accountId, PaymentCurrency paymentCurrency) {
        return (root, query, cb) -> {
            if (accountId <= 0 || paymentCurrency == null) {
                return null;
            }

            WalletId walletId = new WalletId(accountId, paymentCurrency);
            Optional<Wallet> walletOptional = walletRepo.findByWalletIdAndIsActiveEquals(walletId, true);

            return cb.equal(root.get(attribute), walletOptional.get());
        };
    }


    private Specification<Transaction> dateGreaterThanOrEqualTo(String attribute, long value) {
        return (root, query, cb) -> {
            if (value <= 0) {
                return null;
            }
            Date dateValue = new Date(value);

            return cb.greaterThanOrEqualTo(root.get(attribute), dateValue);
        };
    }

    private Specification<Transaction> dateLessThanOrEqualTo(String attribute, long value) {
        return (root, query, cb) -> {
            if (value <= 0) {
                return null;
            }

            Date dateValue = new Date(value);
            return cb.lessThanOrEqualTo(root.get(attribute), dateValue);
        };
    }

     */
}
