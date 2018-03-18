package pe.aloha.app.aloha.UI;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.aloha.app.aloha.Core.Persist;
import pe.aloha.app.aloha.Core.Utils;
import pe.aloha.app.aloha.R;

public class SplahScreenActivity extends AppCompatActivity {

    public static SplahScreenActivity splahScreenActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Persist.Get("token", splahScreenActivity).matches("")) {
                    Utils.changeActivity(splahScreenActivity, SignInActivity.class);
                } else {
                    Utils.createToast(splahScreenActivity, R.string.welcome);
                    Utils.changeActivity(splahScreenActivity, ListServicesActivity.class);
                }

                finish();
            }
        }, 1000);
    }

    private void init() {
        splahScreenActivity = this;
    }
}
