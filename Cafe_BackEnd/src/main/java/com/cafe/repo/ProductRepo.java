package com.cafe.repo;

import com.cafe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByCategoryIdAndNameContaining(Long categoryId, String title);

    List<Product> findAllByCategoryId(Long categoryId);

}
