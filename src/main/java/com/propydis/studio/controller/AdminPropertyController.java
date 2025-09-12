package com.propydis.studio.controller;

import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mongodb.PhotoDTO;
import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.dto.mongodb.mapper.PropertyMapper;
import com.propydis.studio.infrastucture.cloudinary.CloudinaryService;
import com.propydis.studio.infrastucture.validation.ImageValidator;
import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/admin/property")
public class AdminPropertyController {

    private final PropertyService propertyService;
    private final CloudinaryService cloudinaryService;

    public AdminPropertyController(PropertyService propertyService, CloudinaryService cloudinaryService) {
        this.propertyService = propertyService;
        this.cloudinaryService = cloudinaryService;
    }
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> create(@RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam(value = "priceValue", required = false) String priceValue,
                                              @RequestParam(value = "priceText", required = false) String priceText,
                                              @RequestParam(value = "photos", required = false) MultipartFile[] photos) {
        List<Photo> photoEntities = new ArrayList<>();

        if (photos != null) {
            for (MultipartFile photo : photos) {
                try {
                    ImageValidator.validate(photo);
                    Map uploadResult = cloudinaryService.uploadImage(photo);

                    String url = uploadResult.get("secure_url").toString();
                    String publicId = uploadResult.get("public_id").toString();

                    Photo photoEntity = new Photo();
                    photoEntity.setUrl(url);
                    photoEntity.setPublicId(publicId);
                    photoEntity.setAltText(photo.getOriginalFilename());

                    photoEntities.add(photoEntity);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        PropertyCreateDTO dto = new PropertyCreateDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPriceText(priceText);
        if (priceValue != null) dto.setPriceValue(new java.math.BigDecimal(priceValue));
        dto.setPhotos(photoEntities.stream().map(p -> {
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setUrl(p.getUrl());
            photoDTO.setPublicId(p.getPublicId());
            photoDTO.setAltText(p.getAltText());
            return photoDTO;
        }).collect(Collectors.toList()));

        Property property = PropertyMapper.toEntity(dto, photoEntities);
        Property savedProperty = propertyService.save(property, photoEntities);
        PropertyDTO responseDTO = propertyService.getPropertyDTOById(savedProperty.getId());

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable String id,
                                              @RequestParam("name") String name,
                                              @RequestParam("description") String description,
                                              @RequestParam(value = "priceValue", required = false) String priceValue,
                                              @RequestParam(value = "priceText", required = false) String priceText,
                                              @RequestParam(value = "photos", required = false) MultipartFile[] photos,
                                              @RequestParam(value = "deletePhotoIds", required = false) List<String> deletePhotoIds) {
        List<Photo> newPhotoEntities = new ArrayList<>();

        if (photos != null) {
            for (MultipartFile photo : photos) {
                try {
                    ImageValidator.validate(photo);
                    Map uploadResult = cloudinaryService.uploadImage(photo);

                    String url = uploadResult.get("secure_url").toString();
                    String publicId = uploadResult.get("public_id").toString();

                    Photo photoEntity = new Photo();
                    photoEntity.setUrl(url);
                    photoEntity.setPublicId(publicId);
                    photoEntity.setAltText(photo.getOriginalFilename());

                    newPhotoEntities.add(photoEntity);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        PropertyCreateDTO dto = new PropertyCreateDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPriceText(priceText);
        if (priceValue != null) dto.setPriceValue(new java.math.BigDecimal(priceValue));

        Property property = PropertyMapper.toEntity(dto, newPhotoEntities, id);
        Property updatedProperty = propertyService.update(property, newPhotoEntities, deletePhotoIds, id);
        PropertyDTO responseDTO = propertyService.getPropertyDTOById(updatedProperty.getId());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PropertyDTO>> getAll() {
        List<PropertyDTO> properties = propertyService.findAll().stream()
                .map(property -> propertyService.getPropertyDTOById(property.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(properties);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PropertyDTO> getById(@PathVariable String id) {
        PropertyDTO dto = propertyService.getPropertyDTOById(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        propertyService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
