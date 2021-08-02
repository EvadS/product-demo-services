package com.se.product.service.repository;

import com.se.product.service.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsAllByCode(String code);

    boolean existsAllByName(String name);
}
