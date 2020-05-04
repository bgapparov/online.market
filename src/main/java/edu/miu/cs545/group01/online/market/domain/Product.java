package edu.miu.cs545.group01.online.market.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    private  long Id;

    private String title;

    @ManyToOne
    private Category category;

    private float price;

    private String status;

    private String imgName;

    private String description;

    @ManyToOne
    private Seller seller;

    public Product() {
    }

    public Product(String title, Category category, float price, String status, String imgName, String description, Seller seller) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.status = status;
        this.imgName = imgName;
        this.description = description;
        this.seller = seller;
    }

    public long getId() {
        return Id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
