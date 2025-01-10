package com.enterprise.ent_invest_backend.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "investment")
public class Investment {
    @Id
    private String investmentId;
    private String userId;
    private String investorName;
    private String investorJob;
    private String investorInterest;
    private String otherDetails;
    private String budgetLimit;
    private String address;
    private String telNumber;
    private String imageName;
    private byte[] imageData;
    private String contentType;

    public String getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(String investmentId) {
        this.investmentId = investmentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorJob() {
        return investorJob;
    }

    public void setInvestorJob(String investorJob) {
        this.investorJob = investorJob;
    }


    public String getInvestorInterest() {
        return investorInterest;
    }

    public void setInvestorInterest(String investorInterest) {
        this.investorInterest = investorInterest;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getBudgetLimit() {
        return budgetLimit;
    }

    public void setBudgetLimit(String budgetLimit) {
        this.budgetLimit = budgetLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
