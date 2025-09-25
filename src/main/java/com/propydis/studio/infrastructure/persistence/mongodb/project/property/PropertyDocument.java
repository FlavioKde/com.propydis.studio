package com.propydis.studio.infrastructure.persistence.mongodb.project.property;

import com.propydis.studio.domain.project.property.PropertyStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "property")
public class PropertyDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private List<String> photoIds = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private PropertyStatus propertyStatus;
    private BigDecimal priceValue;
    private String priceText;

    public PropertyDocument() {}



    public PropertyDocument(String name, String description, List<String> photoIds, LocalDateTime createdAt, LocalDateTime updatedAt, PropertyStatus propertyStatus,  BigDecimal priceValue, String priceText) {
        this.name = name;
        this.description = description;
        this.photoIds = photoIds != null ? photoIds : new ArrayList<>();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.propertyStatus = propertyStatus;
        this.priceValue = priceValue;
        this.priceText = priceText;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return  description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotoIds() {
        return photoIds;
    }

    public void setPhotoIds(List<String> photoIds) {
        this.photoIds = photoIds != null ? photoIds : new ArrayList<>();
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(BigDecimal priceValue) {
        this.priceValue = priceValue;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }
}
