package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.ProductStatus;
import edu.miu.cs545.group01.online.market.domain.enums.ReviewStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @NotBlank
    @Size(min=4, max = 50, message = "{product.title}")
    private String title;

    @ManyToOne
    @NotNull
    private Category category;

    @Min(value = 0, message = "{product.price}")
    private float price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

//    @NotBlank(message = "{product.imgName}")
    private String imgName;

    @NotBlank
    @Size(min=4, max = 512, message = "{product.description}")
    private String description;

    @ManyToOne
    private Seller seller;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    List<OrderProduct> orderProducts;

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

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
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
        return category+"\r\n"+title;
    }

    public int getAvgStars(){
        int starsSum = 0;
        int reviewsCount = 0;
        for (OrderProduct o :orderProducts) {
            Review review = o.getReview();
            if(review != null && review.getStatus() == ReviewStatus.POSTED) {
                starsSum += review.getStars();
                reviewsCount++;
            }
        }
        if(reviewsCount>0) {
            return Math.round(starsSum/reviewsCount);
        }

        return 5;
    }
    public String getStarsText(){
        String good = "&#9733;";
        String notGood = "&#9734;";
        String result = "";
        int avgStars = getAvgStars();
        for(int i=0; i<5; i++){
            if(i<avgStars) {
                result += good + " ";
            }else{
                result += notGood + " ";
            }
        }
        return result.trim();
    }
    public List<Review> getReviews(){
        return orderProducts.stream().filter(o->o.getReview()!=null).map(o->o.getReview()).collect(Collectors.toList());
    }
}
