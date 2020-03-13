package Tables;

public class Price {

    private Country country;

    private PartsVehicles partsVehicles;

    private String priceDate;

    private double rrp;

    public Price(Country country, PartsVehicles partsVehicles, String priceDate, double rrp) {
        this.country = country;
        this.partsVehicles = partsVehicles;
        this.priceDate = priceDate;
        this.rrp = rrp;
    }
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public PartsVehicles getPartsVehicles() {
        return partsVehicles;
    }

    public void setPartsVehicles(PartsVehicles partsVehicles) {
        this.partsVehicles = partsVehicles;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }

    public double getRrp() {
        return rrp;
    }

    public void setRrp(double rrp) {
        this.rrp = rrp;
    }

}
