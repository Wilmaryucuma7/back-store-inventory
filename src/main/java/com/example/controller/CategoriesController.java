package com.example.controller;

import com.example.models.CategoryEntity;
import com.example.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoryRepository categoryRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryEntity category) {
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            CategoryEntity categoryEntity = CategoryEntity.builder()
                    .categoryId(UUID.randomUUID().toString())
                    .categoryName(category.getCategoryName())
                    .categoryDescription(category.getCategoryDescription())
                    .build();
            categoryRepository.save(categoryEntity);

            response.put("error", false);
            response.put("response", "La categoria se ha agregado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("response", "Ha ocurrido un error al agregar la categoria");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryId){
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            categoryRepository.deleteById(categoryId);
            response.put("error", false);
            response.put("response", "Se ha eliminado la categoria correctamente");
            return ResponseEntity.ok(response);
        } catch (EmptyResultDataAccessException e) {
            response.put("response", "La categoria seleccionada no existe");
            return ResponseEntity.badRequest().body(response);
        } catch (DataIntegrityViolationException e) {
            response.put("response", "La categoria seleccionada esta siendo utilizada por un producto");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("response", "Ha ocurrido un error al eliminar la categoria");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get")
    public ResponseEntity<?> getCategories(){
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();
            response.put("response", categories);
            response.put("error", false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("response", "Ha ocurrido un error al obtener las categorias");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get-by-page/{page}")
    public ResponseEntity<?> getCategoriesByPage(@PathVariable int page){
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            List<CategoryEntity> categories = categoryRepository.getCategoriesByPage(page*10);
            response.put("response", categories);
            response.put("maxPage", Math.abs((categoryRepository.count() - 1) / 10));
            response.put("error", false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("response", "Ha ocurrido un error al obtener las categorias");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
