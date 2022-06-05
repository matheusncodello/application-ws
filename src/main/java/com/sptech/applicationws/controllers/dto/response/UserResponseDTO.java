package com.sptech.applicationws.controllers.dto.response;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String document;
    private AddressResponseDTO fullAddress;
    private Long phoneNumber;
    private String email;
    private Boolean ong;

    public UserResponseDTO(Long id, String username, String document, AddressResponseDTO fullAddress, Long phoneNumber, String email, Boolean ong) {
        this.id = id;
        this.username = username;
        this.document = document;
        this.fullAddress = fullAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ong = ong;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AddressResponseDTO getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(AddressResponseDTO fullAddress) {
        this.fullAddress = fullAddress;
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

    public Boolean getOng() {
        return ong;
    }

    public void setOng(Boolean ong) {
        this.ong = ong;
    }
}
