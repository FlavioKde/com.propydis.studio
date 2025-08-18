package com.propydis.studio.dto.mongodb;

import com.propydis.studio.model.mongodb.Photo;
import com.propydis.studio.model.mongodb.PropertyStatus;

import java.time.LocalDateTime;
import java.util.List;

public class PropertyDTO {

    private String id;
    private String name;
    private String description;
    private List<Photo> photos;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PropertyStatus propertyStatus;


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Photo> getPhotos() { return photos; }
    public void setPhotos(List<Photo> photos) { this.photos = photos; }

    public PropertyStatus getPropertyStatus() { return propertyStatus; }
    public void setPropertyStatus(PropertyStatus propertyStatus) { this.propertyStatus = propertyStatus; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
