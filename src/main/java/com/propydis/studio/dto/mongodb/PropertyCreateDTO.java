package com.propydis.studio.dto.mongodb;

import com.propydis.studio.dto.project.PhotoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PropertyCreateDTO {

    @NotBlank(message = "El nombre de la propiedad es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    private List<PhotoDTO> photos = new ArrayList<>();

    private BigDecimal priceValue;

    private String priceText;

    public PropertyCreateDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<PhotoDTO> getPhotos() { return photos; }
    public void setPhotos(List<PhotoDTO> photos) { this.photos = photos; }

    public BigDecimal getPriceValue() { return priceValue; }
    public void setPriceValue(BigDecimal priceValue) { this.priceValue = priceValue; }

    public String getPriceText() { return priceText; }
    public void setPriceText(String priceText) { this.priceText = priceText; }

}
