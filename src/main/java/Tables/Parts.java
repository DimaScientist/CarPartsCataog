package Tables;

public class Parts {

    private long id;

    private PartsVehicles partsVehicles;

    private char[] brandType;

    private String brand;

    private String descr;

    private String artstat;

    public Parts(long id, PartsVehicles partsVehicles, char[] brandType, String brand, String descr, String artstat) {
        this.id = id;
        this.partsVehicles = partsVehicles;
        this.brandType = brandType;
        this.brand = brand;
        this.descr = descr;
        this.artstat = artstat;
    }



    public PartsVehicles getPartsVehicles() {
        return partsVehicles;
    }

    public void setPartsVehicles(PartsVehicles partsVehicles) {
        this.partsVehicles = partsVehicles;
    }

    public char[] getBrandType() {
        return brandType;
    }

    public void setBrandType(char[] brandType) {
        this.brandType = brandType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getArtstat() {
        return artstat;
    }

    public void setArtstat(String artstat) {
        this.artstat = artstat;
    }

}
