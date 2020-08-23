package co.com.android.baseproject.screens.login;

import co.com.android.baseproject.screens.login.model.Login;
import co.com.android.baseproject.screens.login.model.LoginDTO;
import io.reactivex.Single;
import retrofit2.Call;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public interface ILogin {

    interface ViewModel {
        void doLogin(Login login);
        void getInfoSaved();
    }

    interface Intectaror {
        //Api
        Single<LoginDTO> login(Login login);
        //Local
        Single<Login> getLocalInfo(String key);
    }

    interface Repository {
        //Api
        Call<LoginDTO> loginApi(String email, String password);

        //Local
        Login loginLocal(String key);
        void removeLocalLogin(String key);
        void saveLoginLocal(String key, Login login);
    }
}
