package com.se.product.service.service.impl;

import com.se.product.service.domain.Product;
import com.se.product.service.domain.specification.ProductSpecification;
import com.se.product.service.exception.AlreadyExistException;
import com.se.product.service.exception.model.ResourceNotFoundException;
import com.se.product.service.mapper.ProductMapper;
import com.se.product.service.model.CategoriesRequest;
import com.se.product.service.model.PricesRequest;
import com.se.product.service.model.payload.ProductRequest;
import com.se.product.service.model.payload.ProductResponse;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.repository.PriceRepository;
import com.se.product.service.repository.ProductRepository;
import com.se.product.service.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    private final ProductSpecification productSpecification;


    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository, ProductSpecification productSpecification) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
        this.productSpecification = productSpecification;
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {

        boolean exists = productRepository.existsByName(productRequest.getName());

        if(exists){
            throw  new AlreadyExistException("Product", "name", productRequest.getName());
        }

        // TODO check is prises exists -> maybe should return list ?
        if(productRequest.getPrices() != null ){
            // TODO: how to use stream ?
            for(Long item : productRequest.getPrices().getPrices() ){
                priceRepository.findById(item).orElseThrow(
                        () -> new ResourceNotFoundException("Price", "id", item));
            }
        }

        if(productRequest.getCategories() != null &&
                productRequest.getCategories().getCategories() != null){
            // TODO: how to use stream ?
            for(Long item : productRequest.getCategories().getCategories() ){
                categoryRepository.findById(item).orElseThrow(
                        () -> new ResourceNotFoundException("Category", "id", item));
            }
        }

        Product product = ProductMapper.MAPPER.toProduct(productRequest);

        ProductResponse productResponse = ProductMapper.MAPPER.toProductRepository(product);
        return productResponse;
    }

    @Override
    public ProductResponse update(Long id, ProductRequest product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProductResponse updateCategories(Long id, CategoriesRequest categoriesRequest) {
        return null;
    }

    @Override
    public ProductResponse updatePrices(Long id, PricesRequest pricesRequest) {
        return null;
    }

    /*
     @Override
    public Page<WalletTransactionItemResponse> getPagged(UserBalanceSearchRequest userBalanceSearchRequest,
                                                         WalletId walletId) {

        Pageable pageable = PageRequest.of(
                userBalanceSearchRequest.getPage(),
                userBalanceSearchRequest.getCount(),
                Sort.by("createdAt").descending());

        TransactionSearch transactionSearch = UserBalanceSearchRequestMapper.INSTANCE
                .searchRequestToUserBalanceSearch(userBalanceSearchRequest, walletId.getPaymentCurrency(),
                        walletId.getAccountId());

        Page<WalletTransactionItemResponse> transactionPage = transactionRepo.findAll(transactionSpecification
                .getFilter(transactionSearch), pageable)
                .map(WalletTransactionResponseMapper.INSTANCE::transactionToWalletTransactionResponse);

        return transactionPage;
    }
     */
}
