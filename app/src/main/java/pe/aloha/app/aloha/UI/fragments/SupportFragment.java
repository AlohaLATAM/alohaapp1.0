package pe.aloha.app.aloha.UI.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.aloha.app.aloha.Core.Utils;
import pe.aloha.app.aloha.R;

public class SupportFragment extends Fragment {

    TextView call;
    TextView whatsapp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);

        call = view.findViewById(R.id.button_call);
        whatsapp = view.findViewById(R.id.button_whatsapp);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.makeCall(getContext());
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openWhatsapp(getContext());
            }
        });

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
