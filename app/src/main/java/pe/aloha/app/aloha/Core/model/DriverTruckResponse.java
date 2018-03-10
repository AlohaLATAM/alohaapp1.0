package pe.aloha.app.aloha.Core.model;

/**
 * Created by loualcala on 20/02/18.
 */

public class DriverTruckResponse {
    private Boolean its_furgon;
    private String registration_number;
    private Driver driver;
    private TruckType truck_type;

    public Boolean getIts_furgon() {
        return its_furgon;
    }

    public void setIts_furgon(Boolean its_furgon) {
        this.its_furgon = its_furgon;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public TruckType getTruck_type() {
        return truck_type;
    }

    public void setTruck_type(TruckType truck_type) {
        this.truck_type = truck_type;
    }
}
