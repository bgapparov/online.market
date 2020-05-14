package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.ReviewStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private OrderProduct orderProduct;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Buyer buyer;
    @NotNull(message = "{review.createdDate}")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date createdDate;
    @Min(value = 1, message = "{review.stars}")
    @Max(value = 5, message = "{review.stars}")
    private int stars;
    @NotBlank(message = "{review.comment}")
    private String comment;
    @NotNull(message = "{review.decision}")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date decision;

    public Review() {
    }

    public Review(OrderProduct orderProduct, ReviewStatus status, Buyer buyer, Date createdDate, int stars, String comment, Date decision) {
        this.orderProduct = orderProduct;
        this.status = status;
        this.buyer = buyer;
        this.createdDate = createdDate;
        this.stars = stars;
        this.comment = comment;
        this.decision = decision;
    }

    public long getId() {
        return id;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDecision() {
        return decision;
    }

    public void setDecision(Date decision) {
        this.decision = decision;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public boolean isPosted(){
        return status == ReviewStatus.POSTED;
    }
}
