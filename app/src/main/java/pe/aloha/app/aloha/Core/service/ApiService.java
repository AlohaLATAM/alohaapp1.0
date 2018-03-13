package pe.aloha.app.aloha.Core.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by loualcala on 19/02/18.
 */

public class ApiService {
    public static Api getApiService() {
        return new Retrofit.Builder()
                .baseUrl("https://panel.aloha.pe")
                // .baseUrl("http://192.168.1.4:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }
}
