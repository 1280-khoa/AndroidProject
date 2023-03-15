package com.example.doan.model;

import java.util.List;

public class LoaiHangXeModel {
    boolean success;
    String message;
    List<LoaiHangxe> result;

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

    public List<LoaiHangxe> getResult() {
        return result;
    }

    public void setResult(List<LoaiHangxe> result) {
        this.result = result;
    }


}
