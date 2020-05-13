package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.GainPointType;

import javax.persistence.*;

@Entity
public class GainPoint {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private GainPointType type;

    @OneToOne
    private Order order;

    @ManyToOne
    private Buyer buyer;

    private float point;

    public GainPoint() {
    }

    public GainPoint(GainPointType type, Order order, Buyer buyer, float point) {
        this.type = type;
        this.order = order;
        this.buyer = buyer;
        this.point = point;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GainPointType getType() {
        return type;
    }

    public void setType(GainPointType type) {
        this.type = type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public float getPoint() {
        return point;
    }

    public float getActualPoint(){
        switch (type){
            case REFUND:
            case EARN:
                return point;
            case SPEND:
                return -1*point;

        }
        throw new RuntimeException("Unknown type="+type);
    }

    public void setPoint(float point) {
        this.point = point;
    }
}
