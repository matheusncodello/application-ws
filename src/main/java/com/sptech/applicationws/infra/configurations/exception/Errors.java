package com.sptech.applicationws.infra.configurations.exception;

import java.util.List;

public class Errors {
    private Boolean success;
    private List<String> errors;

    public Errors(String message) {
        this.success = false;
        this.errors = List.of(message);
    }

    public List<String> getErrors() {
        return errors;
    }
    public Boolean getSuccess() {
        return success;
    }
}
