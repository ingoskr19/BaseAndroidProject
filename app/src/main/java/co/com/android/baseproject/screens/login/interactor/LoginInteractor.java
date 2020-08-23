package co.com.android.baseproject.screens.login.interactor;

import android.util.Log;

import co.com.android.baseproject.screens.login.ILogin;
import co.com.android.baseproject.screens.login.model.Login;
import co.com.android.baseproject.screens.login.model.LoginDTO;
import co.com.android.baseproject.util.Constants;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created By oscar.vergara on 21/08/2020
 */
public class LoginInteractor implements ILogin.Intectaror {

    private ILogin.Repository repository;

    public LoginInteractor(ILogin.Repository repository) {
        this.repository = repository;
    }

    @Override
    public Single<LoginDTO> login(Login login) {
        return Single.create( suscriber -> repository.loginApi(login.getEmail(), login.getPassword()).enqueue(new Callback<LoginDTO>() {
            @Override
            public void onResponse(Call<LoginDTO> call, Response<LoginDTO> response) {
                if(login.isRememberMe()){
                    repository.saveLoginLocal(Constants.LOGIN_NAME,login);
                } else {
                    repository.removeLocalLogin(Constants.LOGIN_NAME);
                }
                suscriber.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginDTO> call, Throwable t) {
                Log.e("tt","Tt",t);
                suscriber.onError(t);
            }
        }));
    }

    @Override
    public Single<Login> getLocalInfo(String key) {
        return Single.fromCallable(()-> repository.loginLocal(key));
    }
}
