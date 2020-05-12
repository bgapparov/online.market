package edu.miu.cs545.group01.online.market.domain;

import edu.miu.cs545.group01.online.market.domain.enums.AddressStatus;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Size(min=5,max=6)
    private String zipcode;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    @Size(min=3)
    private String state;

    @NotBlank
    @Size(min=10, max=13)
    private String phoneNo;

    @NotBlank
    @Size(min=3)
    private String country;

    @ManyToOne
    private Buyer buyer;

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
        return id;
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

    @Override
    public String toString() {
        return city + ", " + state + ", " + street + ", " + zipcode + ", " + phoneNo + ", " + country;
    }
}
