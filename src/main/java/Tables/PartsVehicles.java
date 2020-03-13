package Tables;

public class PartsVehicles {

    private long id;

    private Vehicles vehicles;

    public PartsVehicles(long id, Vehicles vehicles) {
        this.id = id;
        this.vehicles = vehicles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }


}
