package pe.aloha.app.aloha.Core.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by loualcala on 20/02/18.
 */

public class QuotationResponse implements Parcelable {
    private String address_from;
    private String address_to;
    private String datetime_of_service;
    private String floor_from;
    private String floor_to;
    private HomeType home_type_from;
    private HomeType home_type_to;
    private String id;
    private String total_price;
    private Driver assigned_driver;
    private ArrayList<Inventory> inventory;

    protected QuotationResponse(Parcel in) {
        address_from = in.readString();
        address_to = in.readString();
        datetime_of_service = in.readString();
        floor_from = in.readString();
        floor_to = in.readString();
        home_type_from = in.readParcelable(HomeType.class.getClassLoader());
        home_type_to = in.readParcelable(HomeType.class.getClassLoader());
        id = in.readString();
        total_price = in.readString();
        assigned_driver = in.readParcelable(Driver.class.getClassLoader());
        inventory = in.readParcelable(Inventory.class.getClassLoader());
    }

    public static final Creator<QuotationResponse> CREATOR = new Creator<QuotationResponse>() {
        @Override
        public QuotationResponse createFromParcel(Parcel in) {
            return new QuotationResponse(in);
        }

        @Override
        public QuotationResponse[] newArray(int size) {
            return new QuotationResponse[size];
        }
    };

    public ArrayList<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Inventory> inventory) {
        this.inventory = inventory;
    }

    public Driver getAssigned_driver() {
        return assigned_driver;
    }

    public void setAssigned_driver(Driver assigned_driver) {
        this.assigned_driver = assigned_driver;
    }

    public String getAddress_from() {
        return address_from;
    }

    public void setAddress_from(String address_from) {
        this.address_from = address_from;
    }

    public String getAddress_to() {
        return address_to;
    }

    public void setAddress_to(String address_to) {
        this.address_to = address_to;
    }

    public String getDatetime_of_service() {
        return datetime_of_service;
    }

    public void setDatetime_of_service(String datetime_of_service) {
        this.datetime_of_service = datetime_of_service;
    }

    public String getFloor_from() {
        return floor_from;
    }

    public void setFloor_from(String floor_from) {
        this.floor_from = floor_from;
    }

    public String getFloor_to() {
        return floor_to;
    }

    public void setFloor_to(String floor_to) {
        this.floor_to = floor_to;
    }

    public HomeType getHome_type_from() {
        return home_type_from;
    }

    public void setHome_type_from(HomeType home_type_from) {
        this.home_type_from = home_type_from;
    }

    public HomeType getHome_type_to() {
        return home_type_to;
    }

    public void setHome_type_to(HomeType home_type_to) {
        this.home_type_to = home_type_to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address_from);
        parcel.writeString(address_to);
        parcel.writeString(datetime_of_service);
        parcel.writeString(floor_from);
        parcel.writeString(floor_to);
        parcel.writeParcelable(home_type_from, i);
        parcel.writeParcelable(home_type_to, i);
        parcel.writeString(id);
        parcel.writeString(total_price);
        parcel.writeParcelable(assigned_driver, i);
        parcel.writeList(inventory);
    }
}
