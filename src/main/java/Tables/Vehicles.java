package Tables;

public class Vehicles {

    private long id;

    private String brand;

    public Vehicles(long id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
