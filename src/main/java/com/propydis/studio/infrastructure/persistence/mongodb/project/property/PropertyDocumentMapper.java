package com.propydis.studio.infrastructure.persistence.mongodb.project.property;

import com.propydis.studio.domain.project.property.Property;

public class PropertyDocumentMapper {

        public static Property toDomain(PropertyDocument propertyDocument) {
            if (propertyDocument == null){
                return null;
            }
            Property property = new Property();
            property.setId(propertyDocument.getId());
            property.setName(propertyDocument.getName());
            property.setDescription(propertyDocument.getDescription());
            property.setPhotoIds(propertyDocument.getPhotoIds());
            property.setCreatedAt(propertyDocument.getCreatedAt());
            property.setUpdatedAt(propertyDocument.getUpdatedAt());
            property.setPropertyStatus(propertyDocument.getPropertyStatus());
            property.setPriceText(propertyDocument.getPriceText());
            property.setPriceValue(propertyDocument.getPriceValue());

            return property;

        }

        public static PropertyDocument toDocument(Property property) {
            if (property == null){
                return null;
            }
            PropertyDocument propertyDocument = new PropertyDocument();
            propertyDocument.setId(property.getId());
            propertyDocument.setName(property.getName());
            propertyDocument.setDescription(property.getDescription());
            propertyDocument.setPhotoIds(property.getPhotoIds());
            propertyDocument.setCreatedAt(property.getCreatedAt());
            propertyDocument.setUpdatedAt(property.getUpdatedAt());
            propertyDocument.setPropertyStatus(property.getPropertyStatus());
            propertyDocument.setPriceText(property.getPriceText());
            propertyDocument.setPriceValue(property.getPriceValue());

            return propertyDocument;
        }
}
