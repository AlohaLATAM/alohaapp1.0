package pe.aloha.app.aloha.Core.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by loualcala on 20/02/18.
 */

public class HomeType implements Parcelable {
    private String name;

    protected HomeType(Parcel in) {
        name = in.readString();
    }

    public static final Creator<HomeType> CREATOR = new Creator<HomeType>() {
        @Override
        public HomeType createFromParcel(Parcel in) {
            return new HomeType(in);
        }

        @Override
        public HomeType[] newArray(int size) {
            return new HomeType[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
