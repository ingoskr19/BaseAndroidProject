package co.com.android.baseproject.screens.login.data.preference;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import co.com.android.baseproject.screens.login.model.Login;
import co.com.android.baseproject.screens.login.model.LoginDTO;
import co.com.android.baseproject.util.Constants;
import co.com.android.baseproject.util.PreferencesUtil;

/**
 * Created By oscar.vergara on 22/08/2020
 */
public class LoginPreferences extends PreferencesUtil {

    public LoginPreferences(Context context) {
        super(context);
    }

    public Login login(String key){
        Login login = new Login();
        login.setEmail((String) getPreference(key.concat(Constants.REMEMBER_EMAIL),""));
        login.setPassword((String) getPreference(key.concat(Constants.REMEMBER_PASS),""));
        login.setRememberMe(!TextUtils.isEmpty(login.getEmail()));
        return login;
    }

    public void saveLogin(String key, Login login){
        setPreference(key.concat(Constants.REMEMBER_EMAIL),login.getEmail());
        setPreference(key.concat(Constants.REMEMBER_PASS),login.getPassword());
    }

    public void removeLogin(String key){
        removePreference(key.concat(Constants.REMEMBER_EMAIL));
        removePreference(key.concat(Constants.REMEMBER_PASS));
    }
}
