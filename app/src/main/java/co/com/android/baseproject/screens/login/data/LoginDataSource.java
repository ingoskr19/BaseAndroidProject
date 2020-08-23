package co.com.android.baseproject.screens.login.data;

import co.com.android.baseproject.screens.login.ILogin;
import co.com.android.baseproject.screens.login.data.preference.LoginPreferences;
import co.com.android.baseproject.screens.login.data.service.LoginApiService;
import co.com.android.baseproject.screens.login.model.Login;
import co.com.android.baseproject.screens.login.model.LoginDTO;
import retrofit2.Call;

/**
 * Created By oscar.vergara on 21/08/2020
 */
public class LoginDataSource implements ILogin.Repository {

    LoginPreferences preferences;
    LoginApiService api;

    public LoginDataSource(LoginPreferences preferences, LoginApiService api) {
        this.preferences = preferences;
        this.api = api;
    }

    @Override
    public Call<LoginDTO> loginApi(String email, String password) {
        return api.login(email, password);
    }

    @Override
    public Login loginLocal(String key) {
        return preferences.login(key);
    }

    @Override
    public void removeLocalLogin(String key) {
        preferences.removeLogin(key);
    }

    @Override
    public void saveLoginLocal(String key, Login login) {
        preferences.saveLogin(key, login);
    }
}
