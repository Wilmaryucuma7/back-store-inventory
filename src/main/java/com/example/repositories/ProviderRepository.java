package com.example.repositories;

import com.example.models.ProviderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends CrudRepository<ProviderEntity, String> {
    @Query(value = "SELECT * FROM providers ORDER BY provider_name ASC LIMIT ?1, 10", nativeQuery = true)
    List<ProviderEntity> getProvidersByPage(int page);
}
