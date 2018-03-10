package pe.aloha.app.aloha.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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

public class ListServicesActivity extends AppCompatActivity {

    static ListServicesActivity listServicesActivity;

    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ServicesListFragment servicesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_services);

        initCast();

        onInit();
    }

    private void initCast() {
        listServicesActivity = this;
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.services_list_placeholder);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        servicesListFragment = new ServicesListFragment();

        setSupportActionBar(toolbar);
    }

    private void onInit() {
        String trucks_ids = Persist.Get("truck_type_ids", listServicesActivity);

        if (!trucks_ids.equals("")) {
            Call<ArrayList<QuotationResponse>> filteredQuotations = ApiService.getApiService().getFilteredQuotations(trucks_ids);
            filteredQuotations.enqueue(new Callback<ArrayList<QuotationResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<QuotationResponse>> call, Response<ArrayList<QuotationResponse>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<QuotationResponse> quotations = response.body();
                        onSuccessGetFilteredQuotations(quotations);
                    } else {
                        Utils.createToast(listServicesActivity, R.string.something_wrong);
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
                return true;
            case R.id.menu_item_my_services:
                return true;
            case R.id.menu_item_profile:
                return true;
            case R.id.menu_item_help:
                return true;
            case R.id.menu_item_logout:
                Persist.Clear(listServicesActivity);
                Utils.changeActivity(listServicesActivity, MainActivity.class);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
