package com.se.product.service.mapper;


import com.se.product.service.domain.Category;
import com.se.product.service.model.CategoryRequest;
import com.se.product.service.model.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    @Mappings({
            @Mapping(source = "baseCategory", target = "baseCategory"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "name", target = "name")
    })
    Category toCategory(CategoryRequest request);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "categoryRequest.baseCategory", source = "baseCategory"),
            @Mapping(target = "categoryRequest.code", source = "code"),
            @Mapping(target = "categoryRequest.name", source = "name")
    })
    CategoryResponse toCategoryResponse(Category item);
}
