package com.propydis.studio.dto.project.photo;


public class PhotoDTO {

    private String id;
    private String url;
    private String altText;
    private String publicId;

    public PhotoDTO() {}

    public PhotoDTO(String url, String altText, String publicId) {
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
