package org.example.Tables;

public class Summary {

    private int oeParts;
    private int iamParts;
    private int wholesaler;

    private String rrpAvg;
    private double rrpRange;

    public int getOeParts() {
        return oeParts;
    }

    public void setOeParts(int oeParts) {
        this.oeParts = oeParts;
    }

    public int getIamParts() {
        return iamParts;
    }

    public void setIamParts(int iamParts) {
        this.iamParts = iamParts;
    }

    public int getWholesaler() {
        return wholesaler;
    }

    public void setWholesaler(int wholesaler) {
        this.wholesaler = wholesaler;
    }

    public String getRrpAvg() {
        return rrpAvg;
    }

    public void setRrpAvg(String rrpAvg) {
        this.rrpAvg = rrpAvg;
    }

    public double getRrpRange() {
        return rrpRange;
    }

    public void setRrpRange(double rrpRange) {
        this.rrpRange = rrpRange;
    }

}
