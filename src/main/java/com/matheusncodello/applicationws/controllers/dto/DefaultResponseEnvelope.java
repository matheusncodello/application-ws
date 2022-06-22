package com.matheusncodello.applicationws.controllers.dto;

public class DefaultResponseEnvelope<T> {
    public Boolean success = null;
    public T result = null;

    public DefaultResponseEnvelope(Boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public DefaultResponseEnvelope() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}