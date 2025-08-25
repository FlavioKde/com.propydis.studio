package org;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.propydis.studio.controller.PropertyController;
import com.propydis.studio.dto.mongodb.PhotoDTO;
import com.propydis.studio.dto.mongodb.PropertyCreateDTO;
import com.propydis.studio.dto.mongodb.mapper.PropertyMapper;
import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.model.mongodb.PropertyStatus;
import com.propydis.studio.service.mongodb.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PropertyControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private PropertyController propertyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(propertyController).build();

        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateProperty() throws Exception {
        PropertyCreateDTO propertyCreateDTO = new PropertyCreateDTO();
        propertyCreateDTO.setName("Casa en Palermo");

        propertyCreateDTO.setDescription("Excelente casa, con vistas a las vias del tren. Para entrar a vivir");

        propertyCreateDTO.setPhotos(List.of(

                new PhotoDTO("https://via.placeholder.com/300x400", "Imagen de prueba1"),
                new PhotoDTO("https://via.placeholder.com/250x400", "Imagen de prueba2"),
                new PhotoDTO("https://via.placeholder.com/300x400", "Imagen de prueba3")


        ));




        Property property = PropertyMapper.toEntity(propertyCreateDTO);
        property.setId("abc123456");
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        property.setPropertyStatus(PropertyStatus.RESERVED);




        Mockito.when(propertyService.save(Mockito.any())).thenReturn(property);

        mockMvc.perform(post("/api/property/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(propertyCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("abc123456"))
                .andExpect(jsonPath("$.name").value("Casa en Palermo"))
                .andExpect(jsonPath("$.description").value("Excelente casa, con vistas a las vias del tren. Para entrar a vivir"))
                .andExpect(jsonPath("$.photos").isArray())
                .andExpect(jsonPath("$.photos[0].url").value("https://via.placeholder.com/300x400"))
                .andExpect(jsonPath("$.photos[0].altText").value("Imagen de prueba1"))
                .andExpect(jsonPath("$.photos[1].url").value("https://via.placeholder.com/250x400"))
                .andExpect(jsonPath("$.photos[1].altText").value("Imagen de prueba2"))
                .andExpect(jsonPath("$.photos[2].url").value("https://via.placeholder.com/300x400"))
                .andExpect(jsonPath("$.photos[2].altText").value("Imagen de prueba3"))
                .andExpect(jsonPath("$.propertyStatus").value("RESERVED"));
    }


}
