package com.sptech.applicationws.domain;

import javax.persistence.*;

@Entity(name = "tb_address")
public class Address{
    @Id
    @Column(name = "fk_user")
    private Long fkUser;
    @Column(name = "address_cep")
    private String cep;
    @Column(name = "address_street")
    private String street;
    @Column(name = "address_street_number")
    private String streetNumber;
    @Column(name = "address_street_complement")
    private String streetComplement;
    @Column(name = "address_district")
    private String district;
    @Column(name = "address_city")
    private String city;
    @Column(name = "address_state")
    private String state;

    public Address(Long fkUser, String cep, String street, String streetNumber, String streetComplement, String district, String city, String state) {
        this.fkUser = fkUser;
        this.cep = cep;
        this.street = street;
        this.streetNumber = streetNumber;
        this.streetComplement = streetComplement;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public Address() {
    }

    public Long getFkUser() {
        return fkUser;
    }

    public void setFkUser(Long fkUser) {
        this.fkUser = fkUser;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetComplement() {
        return streetComplement;
    }

    public void setStreetComplement(String streetComplement) {
        this.streetComplement = streetComplement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
}
