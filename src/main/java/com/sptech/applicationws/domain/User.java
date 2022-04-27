package com.sptech.applicationws.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Column(name = "user_name")
    private String username;

    @NotBlank
    @Column(name = "user_document")
    private String document;

    @NotNull
    @Column(name = "user_phone")
    private Long phoneNumber;

    @Email
    @NotBlank
    @Column(name = "user_email")
    private String email;

    @NotBlank
    @Column(name = "user_password")
    private String password;

    @Column(name = "is_ong")
    private boolean isOng;

    public User(String username, String document, Long phoneNumber, String email, String password, boolean isOng) {
        this.username = username;
        this.document = document;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.isOng = isOng;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public boolean isOng() {
        return isOng;
    }

    public void setOng(boolean ong) {
        isOng = ong;
    }
}
