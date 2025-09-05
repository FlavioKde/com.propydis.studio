package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mongodb.PhotoDTO;
import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.dto.mongodb.mapper.PropertyMapper;
import com.propydis.studio.infrastucture.cloudinary.CloudinaryService;
import com.propydis.studio.infrastucture.validation.ImageValidator;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.service.mongodb.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/admin/property")
public class AdminPropertyController {

    private final PropertyService propertyService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public AdminPropertyController(PropertyService propertyService,  CloudinaryService cloudinaryService) {
        this.propertyService = propertyService;
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> create(@RequestParam("name")String name,
                                              @RequestParam("description") String description,
                                              @RequestParam("priceValue")BigDecimal priceValue,
                                              @RequestParam("priceText") String priceText,
                                              @RequestParam(value = "photos", required = false) MultipartFile[] photos)
    {
        List<PhotoDTO>photoDTOS = new ArrayList<>();

        if (photos != null){
            for (MultipartFile photo : photos){
                try{
                    ImageValidator.validate(photo);

                    String url = cloudinaryService.uploadImage(photo);
                    PhotoDTO photoDTO = new PhotoDTO();
                    photoDTO.setUrl(url);
                    photoDTO.setAltText(photo.getOriginalFilename());
                    photoDTOS.add(photoDTO);
                }catch (IOException e){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build();
                }
            }
        }

        PropertyCreateDTO propertyCreateDTO = new PropertyCreateDTO();

        propertyCreateDTO.setName(name);
        propertyCreateDTO.setDescription(description);
        propertyCreateDTO.setPhotos(photoDTOS);
        propertyCreateDTO.setPriceValue(priceValue);
        propertyCreateDTO.setPriceText(priceText);

        Property property = PropertyMapper.toEntity(propertyCreateDTO);
        Property savedProperty = propertyService.save(property);

        return  ResponseEntity.ok(PropertyMapper.toDTO(savedProperty));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable String id,
                                                      @RequestParam("name") String name,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("priceValue")  BigDecimal priceValue,
                                                      @RequestParam("priceText")  String priceText,
                                                      @RequestParam(value = "photos", required = false) MultipartFile[] photos,
                                                      @RequestParam(value = "deletePhotoIds", required = false) List<String> deletePhotoIds)
    {
        Property existingProperty = propertyService.findById(id);

        if (existingProperty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<PhotoDTO> photoDTOS = PropertyMapper.toDTO(existingProperty).getPhotos();
        if (photoDTOS == null) photoDTOS = new ArrayList<>();


        if (deletePhotoIds != null && !deletePhotoIds.isEmpty()) {
            photoDTOS.removeIf(photo -> deletePhotoIds.contains(photo.getId()));
        }

        if (photos != null) {
            for (MultipartFile photo : photos) {

                try {
                    ImageValidator.validate(photo);

                    String url = cloudinaryService.uploadImage(photo);
                    PhotoDTO photoDTO = new PhotoDTO();
                    photoDTO.setUrl(url);
                    photoDTO.setAltText(photo.getOriginalFilename());
                    photoDTOS.add(photoDTO);
                } catch (IOException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        PropertyCreateDTO propertyCreateDTO = new PropertyCreateDTO();

        propertyCreateDTO.setName(name);
        propertyCreateDTO.setDescription(description);
        propertyCreateDTO.setPhotos(photoDTOS);
        propertyCreateDTO.setPriceValue(priceValue);
        propertyCreateDTO.setPriceText(priceText);


        Property updatedProperty = propertyService.update(PropertyMapper.toEntity(propertyCreateDTO, id), id);

        return  ResponseEntity.ok(PropertyMapper.toDTO(updatedProperty));


    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PropertyDTO>> getAll() {
        List<PropertyDTO> properties = propertyService.findAll()
                .stream()
                .map(PropertyMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(properties);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PropertyDTO> getById(@PathVariable String id) {
        Property property = propertyService.findById(id);

        return ResponseEntity.ok(PropertyMapper.toDTO(property));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        propertyService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
