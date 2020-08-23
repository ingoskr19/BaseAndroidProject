package co.com.android.baseproject.screens.login.data.service;

import co.com.android.baseproject.screens.login.model.LoginDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created By oscar.vergara on 21/08/2020
 */
public interface LoginApiService {
    @GET("/user/auth")
    Call<LoginDTO> login(@Query("email") String email, @Query("password") String password);
}