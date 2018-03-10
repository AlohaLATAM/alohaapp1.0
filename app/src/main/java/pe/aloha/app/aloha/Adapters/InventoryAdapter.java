package pe.aloha.app.aloha.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import pe.aloha.app.aloha.Core.model.Inventory;
import pe.aloha.app.aloha.R;

/**
 * Created by loualcala on 6/03/18.
 */

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {
    private Context context;
    private List<Inventory> inventory;

    public InventoryAdapter(Context context, List<Inventory> inventory) {
        this.context = context;
        this.inventory = inventory;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_inventory, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Inventory item = inventory.get(position);

        Log.e("HERE", new Gson().toJson(item));

        holder.name.setText(item.getItem_name());
        holder.quantity.setText(item.getQuantity() + "");
    }

    @Override
    public int getItemCount() {
        return inventory.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View itemView) {
            super(itemView);

            quantity = itemView.findViewById(R.id.inventory_item_quantity);
            name = itemView.findViewById(R.id.inventory_item_name);
        }

        TextView quantity;
        TextView name;
    }
}
