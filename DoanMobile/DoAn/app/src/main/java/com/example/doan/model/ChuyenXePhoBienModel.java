package com.example.doan.model;

import java.util.List;

public class ChuyenXePhoBienModel {
    boolean success;
    String message;
    List<ChuyenXePhoBien> result;

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

    public List<ChuyenXePhoBien> getResult() {
        return result;
    }

    public void setResult(List<ChuyenXePhoBien> result) {
        this.result = result;
    }


}
