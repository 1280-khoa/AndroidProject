package com.example.doan.model;

import java.util.List;

public class ghemodel {
    boolean success;
    String message;
    List<ghe> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ghe> getResult() {
        return result;
    }

    public void setResult(List<ghe> result) {
        this.result = result;
    }
}
