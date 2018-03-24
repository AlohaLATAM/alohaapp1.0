package pe.aloha.app.aloha.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import pe.aloha.app.aloha.Core.Persist;
import pe.aloha.app.aloha.Core.Utils;
import pe.aloha.app.aloha.Core.model.AssignUserRequest;
import pe.aloha.app.aloha.Core.model.Inventory;
import pe.aloha.app.aloha.Core.model.QuotationResponse;
import pe.aloha.app.aloha.Core.service.ApiService;
import pe.aloha.app.aloha.R;
import pe.aloha.app.aloha.UI.fragments.InventoryFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotationActivity extends AppCompatActivity {

    Toolbar toolbar;
    QuotationActivity quotationActivity;
    QuotationResponse quotation;
    ArrayList<Inventory> inventory;
    String quotation_id;
    TextView quotation_date;
    TextView quotation_from;
    TextView quotation_from_type_home;
    TextView quotation_to;
    TextView quotation_to_type_home;
    TextView quotation_price;
    TextView empty_inventory;
    Button accept_service;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    InventoryFragment inventoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation);

        initCast();
        getQuotation();
    }

    private void initCast() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        quotationActivity = this;
        quotation_id = intent.getStringExtra("quotation_id");
        quotation_date = findViewById(R.id.quotation_date);
        quotation_from = findViewById(R.id.quotation_from);
        quotation_from_type_home = findViewById(R.id.quotation_from_type_home);
        quotation_to = findViewById(R.id.quotation_to);
        quotation_to_type_home = findViewById(R.id.quotation_to_type_home);
        quotation_price = findViewById(R.id.quotation_price);
        empty_inventory = findViewById(R.id.empty_inventory);
        accept_service = findViewById(R.id.accept_service);

        frameLayout = findViewById(R.id.services_list_placeholder);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        inventoryFragment = new InventoryFragment();
    }

    private void getQuotation() {
        Call<QuotationResponse> getQuotation = ApiService.getApiService().getQuotation(quotation_id);
        getQuotation.enqueue(new Callback<QuotationResponse>() {
            @Override
            public void onResponse(Call<QuotationResponse> call, Response<QuotationResponse> response) {
                if (response.isSuccessful()) {
                    quotation = response.body();
                    onSuccessGetQuotation();
                }
            }

            @Override
            public void onFailure(Call<QuotationResponse> call, Throwable t) {}
        });
    }

    private void onSuccessGetQuotation() {
        quotation_date.setText(Utils.convertDate(quotation.getDatetime_of_service()));
        quotation_from.setText(quotation.getAddress_from());
        quotation_from_type_home.setText(quotation.getHome_type_from().getName() + " - Piso " + quotation.getFloor_from());
        quotation_to.setText(quotation.getAddress_to());
        quotation_to_type_home.setText(quotation.getHome_type_to().getName() + " - Piso " + quotation.getFloor_to());
        quotation_price.setText(quotation.getTotal_price());

        if (quotation.getAssigned_driver() == null) {
            accept_service.setVisibility(View.VISIBLE);
        }

        onSuccessGetInventory(quotation.getInventory());
    }

    private void onSuccessGetInventory(ArrayList<Inventory> inventory) {
        if (inventory.size() > 0) {
            Bundle bundle = new Bundle();

            bundle.putParcelableArrayList("inventory", inventory);
            inventoryFragment.setArguments(bundle);

            fragmentTransaction.add(R.id.inventory_placeholder, inventoryFragment, "inventory_list");
            fragmentTransaction.commit();
        } else {
            empty_inventory.setVisibility(View.VISIBLE);
        }
    }

    public void onOpenMap(View view) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.pe/maps/dir/" + quotation.getAddress_from() + "/" + quotation.getAddress_to() + "/"));
        startActivity(mapIntent);
    }

    public void onAcceptService(View view) {
        AssignUserRequest driver = new AssignUserRequest();
        driver.setDriver_id(Persist.Get("token", quotationActivity));

        Call<QuotationResponse> assignDriver = ApiService.getApiService().asignDriver(quotation_id, driver);
        assignDriver.enqueue(new Callback<QuotationResponse>() {
            @Override
            public void onResponse(Call<QuotationResponse> call, Response<QuotationResponse> response) {
                if (response.isSuccessful()) {
                    Utils.createToast(getApplicationContext(), R.string.assigned_service);
                } else {
                    Utils.createToast(getApplicationContext(), R.string.something_wrong);
                }
            }

            @Override
            public void onFailure(Call<QuotationResponse> call, Throwable t) {}
        });
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
