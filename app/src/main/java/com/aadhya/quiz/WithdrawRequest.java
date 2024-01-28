package com.aadhya.quiz;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class WithdrawRequest {
    private  String userId;
    private  String emailId;
    private  String requestedBy;

    public  WithdrawRequest(){

    }

    public WithdrawRequest(String userId, String emailId, String requestedBy) {
        this.userId = userId;
        this.emailId = emailId;
        this.requestedBy = requestedBy;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }


    @ServerTimestamp
    private Date createdAt;


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
