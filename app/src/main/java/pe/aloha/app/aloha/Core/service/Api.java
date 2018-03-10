package pe.aloha.app.aloha.Core.service;

import java.util.ArrayList;
import java.util.List;

import pe.aloha.app.aloha.Core.model.AssignUserRequest;
import pe.aloha.app.aloha.Core.model.DriverTruckResponse;
import pe.aloha.app.aloha.Core.model.Inventory;
import pe.aloha.app.aloha.Core.model.QuotationResponse;
import pe.aloha.app.aloha.Core.model.UserRequest;
import pe.aloha.app.aloha.Core.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by loualcala on 19/02/18.
 */

public interface Api {
    @Headers("Content-Type: application/json")
    @POST("/api/drivers/authorize/")
    Call<UserResponse> signIn(@Body UserRequest body);

    @Headers("Content-Type: application/json")
    @GET("/api/trucks/")
    Call<List<DriverTruckResponse>> getUserTrucks(@Query("driver_id") String driver_id);

    @Headers("Content-Type: application/json")
    @GET("/api/quotations/")
    Call<ArrayList<QuotationResponse>> getFilteredQuotations(@Query("truck_size_type_ids") String truck_size_type_ids);

    @Headers("Content-Type: application/json")
    @GET("/api/quotations/{quotation_id}/")
    Call<QuotationResponse> getQuotation(@Path("quotation_id") String quotation_id);

    @Headers("Content-Type: application/json")
    @PUT("api/quotations/{quotation_id}/")
    Call<QuotationResponse> asignDriver(@Path("quotation_id") String quotation_id,
                                        @Body AssignUserRequest driver_token);
}
