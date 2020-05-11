package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.ReviewStatus;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private OrderProduct orderProduct;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @ManyToOne
    private Buyer buyer;

    private Date createdDate;

    private int stars;

    private String comment;

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
}
