package com.propydis.studio.infrastructure.persistence.mongodb.project.photo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photo")
public class PhotoDocument {

        @Id
        private String id;
        private String url;
        private String altText;
        private String publicId;

        public PhotoDocument() {}

        public PhotoDocument(String url, String altText, String publicId) {
            this.url = url;
            this.altText = altText;
            this.publicId = publicId;
        }



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAltText() {
            return altText;
        }

        public void setAltText(String altText) {
            this.altText = altText;
        }

        public String getPublicId() {
            return publicId;
        }

        public void setPublicId(String publicId) {
            this.publicId = publicId;
        }
    }


