package com.adb.dtos;

public class Receipt {
    private Long id;
    private Long paymentID;
    private Boolean status;

    public Receipt(Long id, Long paymentID, Boolean status) {
        this.id = id;
        this.paymentID = paymentID;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
