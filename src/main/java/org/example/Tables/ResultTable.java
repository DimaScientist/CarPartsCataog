package org.example.Tables;

public class ResultTable {

    private String brandType;
    private String partNumber;
    private String altPartNum;
    private String brand;
    private String description;
    private double rrp;
    private String priceDate;
    private int numberOfReferences;
    private int numberOfVehiclesLinkings;
    private String articleStatus;

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }


    public ResultTable(){}

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getAltPartNum() {
        return altPartNum;
    }

    public void setAltPartNum(String altPartNum) {
        this.altPartNum = altPartNum;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRrp() {
        return rrp;
    }

    public void setRrp(double rrp) {
        this.rrp = rrp;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }

    public int getNumberOfReferences() {
        return numberOfReferences;
    }

    public void setNumberOfReferences(int numberOfReferences) {
        this.numberOfReferences = numberOfReferences;
    }

    public int getNumberOfVehiclesLinkings() {
        return numberOfVehiclesLinkings;
    }

    public void setNumberOfVehiclesLinkings(int numberOfVehiclesLinkings) {
        this.numberOfVehiclesLinkings = numberOfVehiclesLinkings;
    }



}
