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

import pe.aloha.app.aloha.Adapters.QuotationAdapter;
import pe.aloha.app.aloha.R;

public class ServicesListFragment extends Fragment {

    ArrayList listQuotations;
    Bundle bundle;
    RecyclerView recyclerView;

    public ServicesListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getArguments();
        listQuotations = bundle.getParcelableArrayList("list_quotations");

        if (listQuotations == null) {
            Log.e("Empty", "No hay servicios por el momento.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services_list, container, false);
        recyclerView = view.findViewById(R.id.list_services_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        QuotationAdapter adapter = new QuotationAdapter(getContext(), listQuotations);

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
