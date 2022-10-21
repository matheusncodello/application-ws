package com.matheusncodello.applicationws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_user_address")
public class Address{
    @NotNull
    @Id
    @Column(name = "fk_user")
    private Long fkUser;

    @NotBlank
    @Column(name = "user_cep")
    private String cep;

    @NotBlank
    @Column(name = "user_street")
    private String street;

    @NotBlank
    @Column(name = "user_street_number")
    private String streetNumber;

    @Column(name = "user_street_complement")
    private String streetComplement;

    @NotBlank
    @Column(name = "user_district")
    private String district;

    @NotBlank
    @Column(name = "user_city")
    private String city;

    @NotBlank
    @Column(name = "user_state")
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
