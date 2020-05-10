package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.AddressStatus;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private long Id;

    private String zipcode;

    private String street;

    private String city;

    private String state;

    private String phoneNo;

    private String country;

    @ManyToOne
    private Buyer buyer;

    private AddressStatus status;

    public Address() {
    }

    public Address(String zipcode, String street, String city, String state, String phoneNo, String country, Buyer buyer, AddressStatus status) {
        this.zipcode = zipcode;
        this.street = street;
        this.city = city;
        this.state = state;
        this.phoneNo = phoneNo;
        this.country = country;
        this.buyer = buyer;
        this.state = state;
    }

    public long getId() {
        return Id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public AddressStatus getStatus() {
        return status;
    }

    public void setStatus(AddressStatus status) {
        this.status = status;
    }
}
