package pe.aloha.app.aloha.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

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
    TextView empty_list;
    TextView loading;

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
        empty_list = findViewById(R.id.empty_list);
        loading = findViewById(R.id.loading);
        frameLayout = findViewById(R.id.services_list_placeholder);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void onInit() {
        frameLayout.setVisibility(View.GONE);
        empty_list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
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

    public void onRefreshServices(View view) {
        onInit();
    }

    private void onSuccessGetFilteredQuotations(ArrayList<QuotationResponse> quotations) {
        loading.setVisibility(View.GONE);
        if (quotations.size() == 0) {
            empty_list.setVisibility(View.VISIBLE);
        } else {
            frameLayout.setVisibility(View.VISIBLE);
            empty_list.setVisibility(View.GONE);

            openFragment(quotations);
        }
    }

    private void openFragment(ArrayList<QuotationResponse> quotations) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        servicesListFragment = new ServicesListFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("list_quotations", quotations);
        servicesListFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.services_list_placeholder, servicesListFragment, "services_list_fragment");
        fragmentTransaction.addToBackStack(null);
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
                Utils.changeActivity(getApplicationContext(), SupportActivity.class);
                return true;
            case R.id.menu_item_logout:
                Persist.Clear(getApplicationContext());
                Utils.changeActivity(getApplicationContext(), SignInActivity.class);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
