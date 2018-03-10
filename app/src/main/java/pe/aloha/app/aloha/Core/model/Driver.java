package pe.aloha.app.aloha.Core.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by loualcala on 20/02/18.
 */

public class Driver implements Parcelable {
    private String first_name;
    private String last_name;
    private String hash_id;
    private String phone_number;
    private String license_number;

    protected Driver(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        hash_id = in.readString();
        phone_number = in.readString();
        license_number = in.readString();
    }

    public static final Creator<Driver> CREATOR = new Creator<Driver>() {
        @Override
        public Driver createFromParcel(Parcel in) {
            return new Driver(in);
        }

        @Override
        public Driver[] newArray(int size) {
            return new Driver[size];
        }
    };

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getHash_id() {
        return hash_id;
    }

    public void setHash_id(String hash_id) {
        this.hash_id = hash_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(hash_id);
        parcel.writeString(phone_number);
        parcel.writeString(license_number);
    }
}
