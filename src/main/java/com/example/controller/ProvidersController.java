package com.example.controller;

import com.example.models.ProviderEntity;
import com.example.repositories.ProviderRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/providers")
public class ProvidersController {
    @Autowired
    private ProviderRepository providerRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addProvider(@Valid @RequestBody ProviderEntity provider){
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            ProviderEntity providerEntity = ProviderEntity.builder()
                    .providerId(UUID.randomUUID().toString())
                    .providerName(provider.getProviderName())
                    .providerEmail(provider.getProviderEmail())
                    .providerPhone(provider.getProviderPhone())
                    .providerAddress(provider.getProviderAddress())
                    .build();
            providerRepository.save(providerEntity);

            response.put("error", false);
            response.put("response", "El proveedor se ha agregado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("response", "Ha ocurrido un error al agregar el proveedor");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{providerId}")
    public ResponseEntity<?> deleteProvider(@PathVariable String providerId) {
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            providerRepository.deleteById(providerId);
            response.put("error", false);
            response.put("response", "Se ha eliminado el proveedor correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println(e.getClass());
            response.put("response", "Ha ocurrido un error al eliminar el proveedor");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("get/{page}")
    public ResponseEntity<?> getProviders(@PathVariable int page){
        JSONObject response = new JSONObject().appendField("error", true);
        try {
            List<ProviderEntity> providers = (List<ProviderEntity>) providerRepository.findAll();
            response.put("response", providers);
            //response.put("maxPage", Math.floorDiv(categoryRepository.count() - 1, 10));
            response.put("error", false);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("response", "Ha ocurrido un error al obtener los proveedores");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
