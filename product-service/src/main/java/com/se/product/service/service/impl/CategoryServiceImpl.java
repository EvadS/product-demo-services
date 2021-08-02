package com.se.product.service.service.impl;

import com.se.product.service.domain.Category;
import com.se.product.service.exception.AlreadyExistException;
import com.se.product.service.exception.model.ResourceNotFoundException;
import com.se.product.service.mapper.CategoryMapper;
import com.se.product.service.model.CategoryRequest;
import com.se.product.service.model.CategoryResponse;
import com.se.product.service.model.CategoryResponseList;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //ResourceNotFoundException
    // AlreadyExistException
    @Override
    public CategoryResponse create(CategoryRequest request) {
        validateCategoryName(request.getName());
        validateCategoryCode(request.getCode());
        validateBaseCategory(request.getBaseCategory());

        Category category = CategoryMapper.MAPPER.toCategory(request);


        category = categoryRepository.save(category);

        return CategoryMapper.MAPPER.toCategoryResponse(category);

    }

    private void validateCategoryCode(String categoryCode) {

        boolean exists = categoryRepository.existsAllByCode(categoryCode);

        if (exists)
            throw new AlreadyExistException("Category", "code", categoryCode);
    }

    private void validateCategoryName(String categoryName) {
        boolean exists = categoryRepository.existsAllByName(categoryName);

        if (exists)
            throw new AlreadyExistException("Category", "name", categoryName);
    }

    private void validateBaseCategory(Long baseCategoryId) {
        boolean exists;
        if (baseCategoryId != null && baseCategoryId > 0) {
            exists = categoryRepository.existsById(baseCategoryId);

            if (exists) {
                throw new ResourceNotFoundException("Category", "base category id", baseCategoryId);
            }
        }
    }

    @Override
    public CategoryResponse updateItem(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        validateCategoryName(request.getName());
        validateCategoryCode(request.getCode());
        validateBaseCategory(request.getBaseCategory());

        category.setBaseCategory(request.getBaseCategory());
        category.setCode(request.getCode());
        category.setName(request.getName());

        categoryRepository.save(category);

        return CategoryMapper.MAPPER.toCategoryResponse(category);

    }

    @Override
    public CategoryResponse changeBase(Long id, Long baseId) {
        return null;
    }

    @Override
    public void deletePrice(Long id) {
        // TODO: check is categories uses
        Category item = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(item);
    }

    @Override
    public CategoryResponse getById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> CategoryMapper.MAPPER.toCategoryResponse(category))
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @Override
    public CategoryResponseList getAll() {
        List<CategoryResponse> categoriesList = categoryRepository.findAll()
                .stream()
                .map(CategoryMapper.MAPPER::toCategoryResponse)
                .collect(Collectors.toList());
        logger.debug("Get all categories. Found: {} items.", categoriesList.size());

        CategoryResponseList categoryResponseList = new CategoryResponseList();
        categoryResponseList.setCategories(categoriesList);
        return categoryResponseList;
    }
}
