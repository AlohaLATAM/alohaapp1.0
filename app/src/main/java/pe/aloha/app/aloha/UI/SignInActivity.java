package pe.aloha.app.aloha.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.aloha.app.aloha.Core.Persist;
import pe.aloha.app.aloha.Core.Utils;
import pe.aloha.app.aloha.Core.model.DriverTruckResponse;
import pe.aloha.app.aloha.R;
import pe.aloha.app.aloha.Core.model.UserRequest;
import pe.aloha.app.aloha.Core.model.UserResponse;
import pe.aloha.app.aloha.Core.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView signup_link;
    TextView forgotpassword_link;
    TextView help_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.signin_username);
        password = findViewById(R.id.signin_password);
        signup_link = findViewById(R.id.signin_signup_link);
        forgotpassword_link = findViewById(R.id.signin_forgotpassword_link);
        help_link = findViewById(R.id.signin_helplink);
    }

    public void goToHelp(View view) {
        Utils.changeActivity(getApplicationContext(), TemporalActivity.class);
    }

    public void SignIn(View view) {
        UserRequest user = new UserRequest();

        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());

        Call<UserResponse> signIn = ApiService.getApiService().signIn(user);
        signIn.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                    onSuccessfullSignIn(response.body().getToken());
                } else if (response.code() == 401) {
                    Toast.makeText(getApplicationContext(), "Error: Los datos ingresados son incorrectos.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error: Ocurrió un problema, intenta comunicarte con nosotros.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {}
        });
    }

    private void onSuccessfullSignIn(String token) {
        Persist.Set("token", token, getApplicationContext());

        Call<List<DriverTruckResponse>> userTrucks = ApiService.getApiService().getUserTrucks(token);
        userTrucks.enqueue(new Callback<List<DriverTruckResponse>>() {
            @Override
            public void onResponse(Call<List<DriverTruckResponse>> call, Response<List<DriverTruckResponse>> response) {
                if (response.isSuccessful()) {
                    List<DriverTruckResponse> trucks = response.body();
                    onSuccessfullGetUserTrucks(trucks);
                } else {
                    Toast.makeText(getApplicationContext(), "Error: Ocurrió un problema, intenta comunicarte con nosotros.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DriverTruckResponse>> call, Throwable t) {}
        });
    }

    private void onSuccessfullGetUserTrucks(List<DriverTruckResponse> trucks) {
        String trucks_ids = "";

        for (int i = 0; i < trucks.size(); i ++) {
            trucks_ids += trucks.get(i).getTruck_type().getId().toString();

            if (i < trucks.size() - 1) {
                trucks_ids += ",";
            }
        }

        Persist.Set("truck_type_ids", trucks_ids, getApplicationContext());

        Utils.changeActivity(getApplicationContext(), ListServicesActivity.class);
    }

    @Override
    public void onBackPressed() {}
}
