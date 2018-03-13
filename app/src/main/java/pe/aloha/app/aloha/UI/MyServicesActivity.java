package pe.aloha.app.aloha.UI;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;

import pe.aloha.app.aloha.Core.Persist;
import pe.aloha.app.aloha.Core.Utils;
import pe.aloha.app.aloha.Core.model.QuotationResponse;
import pe.aloha.app.aloha.Core.service.ApiService;
import pe.aloha.app.aloha.R;
import pe.aloha.app.aloha.UI.fragments.ServicesListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyServicesActivity extends AppCompatActivity {

    static MyServicesActivity myServicesActivity;

    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ServicesListFragment servicesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services);

        initCast();

        onInit();
    }

    private void initCast() {
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.services_list_placeholder);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        servicesListFragment = new ServicesListFragment();

        setSupportActionBar(toolbar);
    }

    private void onInit() {
        String driver_id = Persist.Get("token", getApplicationContext());

        if (!driver_id.equals("")) {
            Call<ArrayList<QuotationResponse>> myQuotations = ApiService.getApiService().getMyQuotations(driver_id);
            myQuotations.enqueue(new Callback<ArrayList<QuotationResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<QuotationResponse>> call, Response<ArrayList<QuotationResponse>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<QuotationResponse> quotations = response.body();
                        onSuccessGetFilteredQuotations(quotations);
                    } else {
                        Utils.createToast(getApplicationContext(), R.string.something_wrong);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<QuotationResponse>> call, Throwable t) {}
            });
        }
    }

    private void onSuccessGetFilteredQuotations(ArrayList<QuotationResponse> quotations) {
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("list_quotations", quotations);
        servicesListFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.services_list_placeholder, servicesListFragment, "services_list_fragment");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_home:
                Utils.changeActivity(getApplicationContext(), ListServicesActivity.class);
                return true;
            case R.id.menu_item_my_services:
                Utils.changeActivity(getApplicationContext(), MyServicesActivity.class);
                return true;
            case R.id.menu_item_profile:
                return true;
            case R.id.menu_item_help:
                return true;
            case R.id.menu_item_logout:
                Persist.Clear(getApplicationContext());
                Utils.changeActivity(getApplicationContext(), MainActivity.class);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
