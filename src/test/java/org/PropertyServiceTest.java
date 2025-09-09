package org;

import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import com.propydis.studio.infrastucture.cloudinary.CloudinaryService;
import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.Property;
import com.propydis.studio.model.mongodb.PropertyStatus;
import com.propydis.studio.repository.mongodb.PhotoRepository;
import com.propydis.studio.repository.mongodb.PropertyRepository;
import com.propydis.studio.service.mongodb.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyService propertyService;


    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private CloudinaryService cloudinaryService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveProperty(){
        Property property = new Property();
        property.setId("abd123456");
        property.setName("Casa en Palermo");
        property.setDescription("Excelente casa, con vistas a las vías del tren");
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        property.setPropertyStatus(PropertyStatus.RESERVED);
        property.setPriceValue(new BigDecimal("12000.99"));
        property.setPriceText("En proceso");


        Photo photo1 = new Photo("https://picsum.photos/300x400", "Imagen de prueba", "publicId1");
        Photo photo2 = new Photo("https://picsum.photos/250x400", "Imagen de prueba", "publicId2");

        List<Photo> photos = List.of(photo1, photo2);
        List<String> photoIds = List.of("photoId1", "photoId2");

        // Simular que las fotos se guardan y reciben IDs
        photo1.setId("photoId1");
        photo2.setId("photoId2");

        property.setPhotoIds(photoIds);
        when(photoRepository.saveAll(anyList())).thenReturn(photos);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property savedProperty = propertyService.save(property, photos);

        assertNotNull(savedProperty);
        assertEquals(2, savedProperty.getPhotoIds().size());
        verify(propertyRepository, times(1)).save(any(Property.class));

    }

    @Test
    public void testUpdateProperty() {
        Property property = new Property();
        property.setId("abd123456");
        property.setName("Casa en Palermo");
        property.setDescription("Excelente casa, con vistas a las vías del tren. Para entrar a vivir");
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        property.setPropertyStatus(PropertyStatus.RESERVED);
        property.setPriceValue(new BigDecimal("12000.00"));
        property.setPriceText("Sin precio");

        Photo photo1 = new Photo("https://picsum.photos/300x400", "Imagen de prueba", "publicId1");
        Photo photo2 = new Photo("https://picsum.photos/250x400", "Imagen de prueba", "publicId2");
        Photo photo3 = new Photo("https://picsum.photos/300x400", "Imagen de prueba3", "publicId3");

        List<Photo> newPhotos = List.of(photo1, photo2);
        List<String> existingPhotoIds = new ArrayList<>(List.of("photoId3"));
        property.setPhotoIds(existingPhotoIds);

        // Simular que las fotos nuevas reciben IDs al guardarse
        when(photoRepository.saveAll(anyList())).thenAnswer(invocation -> {
            List<Photo> inputPhotos = invocation.getArgument(0);
            int counter = 1;
            for (Photo photo : inputPhotos) {
                photo.setId("photoId" + counter++);
            }
            return inputPhotos;
        });

        when(propertyRepository.findById("abd123456")).thenReturn(Optional.of(property));
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        Property updatedProperty = propertyService.update(property, newPhotos, List.of(), "abd123456");

        assertNotNull(updatedProperty);
        assertEquals(3, updatedProperty.getPhotoIds().size());
        assertEquals("Excelente casa, con vistas a las vías del tren. Para entrar a vivir", updatedProperty.getDescription());

        verify(propertyRepository, times(1)).findById("abd123456");
        verify(propertyRepository, times(1)).save(any(Property.class));
    }
    @Test
    public void testFindPropertyById() {
        Property property = new Property();
        property.setId("abd123456");
        property.setName("Casa en Palermo");
        property.setDescription("Excelente casa, con vistas a las vías del tren. Para entrar a vivir");
        property.setPhotoIds(List.of("photoId1", "photoId2", "photoId3"));
        property.setCreatedAt(LocalDateTime.now());
        property.setUpdatedAt(LocalDateTime.now());
        property.setPropertyStatus(PropertyStatus.RESERVED);

        when(propertyRepository.findById("abd123456")).thenReturn(Optional.of(property));

        Property propertyFindById = propertyService.findById("abd123456");

        assertNotNull(propertyFindById);
        assertEquals(property, propertyFindById);
        assertEquals(3, propertyFindById.getPhotoIds().size());

        verify(propertyRepository, times(1)).findById("abd123456");
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
