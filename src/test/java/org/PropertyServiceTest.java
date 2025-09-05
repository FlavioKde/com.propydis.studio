package org;

import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.model.mongodb.PropertyStatus;
import com.propydis.studio.repository.mongodb.PropertyRepository;
import com.propydis.studio.service.mongodb.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveProperty(){
        Property property = new Property();

        property.setId("abd123456");
        property.setName("Casa en Palermo");
        property.setDescription("Excelente casa, con vistas a las vias del tren");

        Photo photo1 = new Photo("https://picsum.photos/300x400", "Imagen de prueba");
        Photo photo2 = new Photo("https://picsum.photos/250x400", "Imagen de prueba");

        property.setPhotos(List.of(photo1,photo2));
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        property.setPropertyStatus(PropertyStatus.RESERVED);
        property.setPriceValue(new BigDecimal("12000.99"));
        property.setPriceText("En proceso");

        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property savedProperty = propertyService.save(property);

        assertNotNull(savedProperty);
        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    @Test
    public void testUpdateProperty(){
        Property property = new Property();

        property.setId("abd123456");
        property.setName("Casa en Palermo");
        property.setDescription("Excelente casa, con vistas a las vias del tren. Para entrar a vivir");

        Photo photo1 = new Photo("https://picsum.photos/300x400", "Imagen de prueba1");
        Photo photo2 = new Photo("https://picsum.photos/250x400", "Imagen de prueba2");
        Photo photo3 = new Photo("https://picsum.photos/300x400", "Imagen de prueba3");

        property.setPhotos(List.of(photo1,photo2,photo3));
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        property.setPropertyStatus(PropertyStatus.RESERVED);
        property.setPriceValue(new  BigDecimal("12000.00"));
        property.setPriceText("Sin precio");

        when(propertyRepository.findById("abd123456")).thenReturn(Optional.of(property));

        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property updatedProperty = propertyService.update(property,"abd123456");
        assertEquals(3, updatedProperty.getPhotos().size());
        assertEquals("Excelente casa, con vistas a las vias del tren. Para entrar a vivir", updatedProperty.getDescription());
        assertNotNull(updatedProperty);
        verify(propertyRepository, times(1)).findById("abd123456");
        verify(propertyRepository, times(1)).save(any(Property.class));

    }

    @Test
    public void testUpdateProperty_NotFound(){

        when(propertyRepository.findById("abd123457")).thenReturn(Optional.empty());

        //Property propertyNotFound = new Property();

        assertThrows(NotFoundByIdException.class,
                () -> propertyService.update(new Property(), "abd123457"));

    }

    @Test
    public void testFindPropertyById(){
        Property property = new Property();

        property.setId("abd123456");
        property.setName("Casa en Palermo");
        property.setDescription("Excelente casa, con vistas a las vias del tren. Para entrar a vivir");

        Photo photo1 = new Photo("https://via.placeholder.com/300x400", "Imagen de prueba1");
        Photo photo2 = new Photo("https://via.placeholder.com/250x400", "Imagen de prueba2");
        Photo photo3 = new Photo("https://via.placeholder.com/300x400", "Imagen de prueba3");

        property.setPhotos(List.of(photo1,photo2,photo3));
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        property.setPropertyStatus(PropertyStatus.RESERVED);

        when(propertyRepository.findById("abd123456")).thenReturn(Optional.of(property));

        Property propertyFindById = propertyService.findById("abd123456");

        assertNotNull(propertyFindById);
        assertEquals(property, propertyFindById);

        verify(propertyRepository, times(1)).findById("abd123456");

    }

    @Test
    public void testFindPropertyById_NotFound(){

        when(propertyRepository.findById("abd123457")).thenReturn(Optional.empty());

        //Property propertyFindByIdNotFound = new Property();

        assertThrows(NotFoundByIdException.class,
                () -> propertyService.findById("abd123457"));

    }

    @Test
    public void testDeleteProperty(){

        Property property = new Property();

        property.setId("abc123457");


        when(propertyRepository.findById("abc123457")).thenReturn(Optional.of(property));
        doNothing().when(propertyRepository).deleteById("abc123457");

        propertyService.deleteById("abc123457");

        verify(propertyRepository, times(1)).delete(property);

    }
    
    @Test
    public void testDeleteProperty_NotFound(){

        when(propertyRepository.findById("abd123457")).thenReturn(Optional.empty());

        assertThrows(NotFoundByIdException.class, () -> propertyService.findById("12345aab"));

        verify(propertyRepository, times(1)).findById("12345aab");
        
    }
}
