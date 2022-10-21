package com.matheusncodello.applicationws.controllers.dto.request;

public class UserRegisterRequestDTO {
    private String username;
    private String document;
    private String cep;
    private String street;
    private String streetNumber;
    private String streetComplement;
    private String district;
    private String city;
    private String state;
    private Long phoneNumber;
    private String email;
    private String password;
    private boolean ong;

    public UserRegisterRequestDTO(String username, String document, String cep, String street, String streetNumber, String streetComplement, String district, String city, String state, Long phoneNumber, String email, String password, boolean ong) {
        this.username = username;
        this.document = document;
        this.cep = cep;
        this.street = street;
        this.streetNumber = streetNumber;
        this.streetComplement = streetComplement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.ong = ong;
    }

    public UserRegisterRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getOng() {
        return ong;
    }

    public void setOng(boolean ong) {
        this.ong = ong;
    }
}
