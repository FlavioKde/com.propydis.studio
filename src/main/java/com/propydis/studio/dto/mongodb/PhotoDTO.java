package com.propydis.studio.dto.mongodb;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PhotoDTO {

    private String id;
    private String url;
    private String altText;

    public PhotoDTO() {}

    public PhotoDTO(String url, String altText) {
        this.url = url;
        this.altText = altText;
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
}
