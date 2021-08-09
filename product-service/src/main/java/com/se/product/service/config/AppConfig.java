package com.se.product.service.config;

import com.se.product.service.constant.GeneralConstants;
import com.se.product.service.controller.base.CategoryApi;
import com.se.product.service.controller.base.PriceApi;
import com.se.product.service.controller.base.ProductApi;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(GeneralConstants.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(CategoryApi.class));
        configurer.addPathPrefix(GeneralConstants.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(PriceApi.class));
        configurer.addPathPrefix(GeneralConstants.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(ProductApi.class));
    }
}
