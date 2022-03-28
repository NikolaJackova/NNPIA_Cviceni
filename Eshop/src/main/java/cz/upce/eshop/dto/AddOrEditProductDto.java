package cz.upce.eshop.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;

public class AddOrEditProductDto {
    private Long id;
    private String productName;
    private String description;
    private MultipartFile image;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
