package com.example.controller;

import com.example.models.ProductEntity;
import com.example.repositories.ProductRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductEntity product) {
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            ProductEntity productEntity = ProductEntity.builder()
                    .productId(UUID.randomUUID().toString())
                    .productName(product.getProductName())
                    .productDescription(product.getProductDescription())
                    .productPrice(product.getProductPrice())
                    .productQuantity(product.getProductQuantity())
                    .category(product.getCategory())
                    .provider(product.getProvider())
                    .build();
            productRepository.save(productEntity);

            response.put("error", false);
            response.put("response", "El producto se ha agregado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            response.put("response", "Ha ocurrido un error al agregar el producto");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId){
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            productRepository.deleteById(productId);
            response.put("error", false);
            response.put("response", "Se ha eliminado el producto correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e.getClass());
            response.put("response", "Ha ocurrido un error al eliminar el producto");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get")
    public ResponseEntity<?> getCategories(){
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
            response.put("response", products);
            //response.put("maxPage", Math.floorDiv(categoryRepository.count() - 1, 10));
            response.put("error", false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("response", "Ha ocurrido un error al obtener los productos");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}