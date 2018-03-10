package pe.aloha.app.aloha.Core.model;

/**
 * Created by loualcala on 20/02/18.
 */

public class TruckType {
    private Integer id;
    private String name;
    private Integer time_per_service;
    private Integer hour_price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime_per_service() {
        return time_per_service;
    }

    public void setTime_per_service(Integer time_per_service) {
        this.time_per_service = time_per_service;
    }

    public Integer getHour_price() {
        return hour_price;
    }

    public void setHour_price(Integer hour_price) {
        this.hour_price = hour_price;
    }
}
