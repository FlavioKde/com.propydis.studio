package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.dto.mongodb.mapper.PropertyMapper;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.service.mongodb.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/admin/property")
@PreAuthorize("hasRole('ADMIN')")
public class AdminPropertyController {

    private final PropertyService propertyService;

    @Autowired
    public AdminPropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/save")
    public ResponseEntity<PropertyDTO> create(@Valid @RequestBody PropertyCreateDTO propertyCreateDTO) {
        Property property = PropertyMapper.toEntity(propertyCreateDTO);
        Property savedProperty = propertyService.save(property);

        return new ResponseEntity<>(PropertyMapper.toDTO(savedProperty), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO>updateProperty(@PathVariable String id,@Valid @RequestBody PropertyCreateDTO propertyCreateDTO) {
        Property property = PropertyMapper.toEntity(propertyCreateDTO);
        Property updated = propertyService.update(property,id);

        return ResponseEntity.ok(PropertyMapper.toDTO(updated));

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PropertyDTO>> getAll() {
        List<PropertyDTO> properties = propertyService.findAll()
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(properties);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PropertyDTO> getById(@PathVariable String id) {
        Property property = propertyService.findById(id);

        return ResponseEntity.ok(PropertyMapper.toDTO(property));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        propertyService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
