package edu.miu.cs545.group01.online.market.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class ProductImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//    @NotBlank(message = "{productimage.imgname}")
    private String imgName;

    private MultipartFile image;

    public ProductImageModel() {
    }

    public ProductImageModel(long id, String imgName) {
        this.id = id;
        this.imgName = imgName;
    }

    public String getImgName() {
        return imgName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
