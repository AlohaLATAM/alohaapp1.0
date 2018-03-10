package pe.aloha.app.aloha.UI.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pe.aloha.app.aloha.Adapters.InventoryAdapter;
import pe.aloha.app.aloha.R;

public class InventoryFragment extends Fragment {

    ArrayList inventory;
    Bundle bundle;
    RecyclerView recyclerView;

    public InventoryFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getArguments();
        inventory = bundle.getParcelableArrayList("inventory");

        if (inventory == null) {
            Log.e("Empty", "No hay inventario.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        recyclerView = view.findViewById(R.id.inventory_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        InventoryAdapter adapter = new InventoryAdapter(getContext(), inventory);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
