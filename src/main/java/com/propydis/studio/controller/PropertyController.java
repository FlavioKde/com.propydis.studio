package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import com.propydis.studio.dto.mongodb.ProjectDTO;
import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.PropertyDTO;
import com.propydis.studio.dto.mongodb.mapper.PropertyMapper;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.service.mongodb.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
            /*
            List<PropertyDTO> properties = propertyService.findAll()
                    .stream()
                    .map(PropertyMapper::toDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(properties);

             */
            List<PropertyDTO> properties = propertyService.findAll().stream()
                    .map(property -> propertyService.getPropertyDTOById(property.getId()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(properties);



        }

        @GetMapping("/get/{id}")
        public ResponseEntity<PropertyDTO> getById(@PathVariable String id) {

            /*
            Property property = propertyService.findById(id);

            return ResponseEntity.ok(PropertyMapper.toDTO(property));


             */
            PropertyDTO dto = propertyService.getPropertyDTOById(id);
            return ResponseEntity.ok(dto);

        }


}
