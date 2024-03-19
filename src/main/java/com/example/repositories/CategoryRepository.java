package com.example.repositories;

import com.example.models.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, String> {
    @Query(value = "SELECT * FROM categories ORDER BY category_name ASC LIMIT ?1, 10", nativeQuery = true)
    List<CategoryEntity> getCategoriesByPage(int page);
}
