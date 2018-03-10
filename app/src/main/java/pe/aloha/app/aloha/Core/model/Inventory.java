package pe.aloha.app.aloha.Core.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by loualcala on 6/03/18.
 */

public class Inventory implements Parcelable {
    private String item_name;
    private int quantity;

    protected Inventory(Parcel in) {
        item_name = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<Inventory> CREATOR = new Creator<Inventory>() {
        @Override
        public Inventory createFromParcel(Parcel in) {
            return new Inventory(in);
        }

        @Override
        public Inventory[] newArray(int size) {
            return new Inventory[size];
        }
    };

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(item_name);
        parcel.writeInt(quantity);
    }
}
