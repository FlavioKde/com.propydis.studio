package com.propydis.studio.dto.mongodb;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ProjectCreateDTO {

    @NotBlank(message = "El campo del nombre no puede estar vacio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @NotBlank(message = "La descripción no puede estar vacia")
    private String description;

    private List<PhotoDTO> photosDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoDTO> getPhotosDTO() {
        return photosDTO;
    }
    public void setPhotosDTO(List<PhotoDTO> photosDTO) {
        this.photosDTO = photosDTO;
    }


}
