package com.matheusncodello.applicationws.controllers.dto.response;

public class AddressResponseDTO {
    private String cep;
    private String street;
    private String streetNumber;
    private String streetComplement;
    private String district;
    private String city;
    private String state;

    public AddressResponseDTO(String cep, String street, String streetNumber, String streetComplement, String district, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.streetNumber = streetNumber;
        this.streetComplement = streetComplement;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public AddressResponseDTO() {
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
