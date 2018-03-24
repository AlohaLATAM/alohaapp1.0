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

import pe.aloha.app.aloha.Core.Utils;
import pe.aloha.app.aloha.R;
import pe.aloha.app.aloha.UI.fragments.SupportFragment;

public class TemporalActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SupportFragment supportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporal);

        initCast();
    }

    private void initCast() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        frameLayout = findViewById(R.id.services_list_placeholder);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        supportFragment = new SupportFragment();
        fragmentTransaction.add(R.id.support_placeholder, supportFragment, "support_fragment");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_sign_in:
                Utils.changeActivity(getApplicationContext(), SignInActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
