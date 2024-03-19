package com.example.repositories;

import com.example.models.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, String> {
    @Query(value = "SELECT * FROM products ORDER BY product_name ASC LIMIT ?1, 10", nativeQuery = true)
    List<ProductEntity> getProductsByPage(int i);
}
