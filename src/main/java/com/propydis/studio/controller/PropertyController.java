package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.service.PropertyService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + "/property")
public class PropertyController {

        private final PropertyService propertyService;

        public PropertyController(PropertyService propertyService) {
            this.propertyService = propertyService;
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


}
