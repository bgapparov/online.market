package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.ProductStatus;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    private String title;

    @ManyToOne
    private Category category;

    private float price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private String imgName;

    private String description;

    @ManyToOne
    private Seller seller;


    @Transient
    private MultipartFile image;

    public Product() {
    }

    public Product(String title, Category category, float price, ProductStatus status, String imgName, String description, Seller seller) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.status = status;
        this.imgName = imgName;
        this.description = description;
        this.seller = seller;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return title + ". price: " + price;
    }
}
