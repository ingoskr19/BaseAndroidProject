package co.com.android.baseproject.session;

import co.com.android.baseproject.screens.login.model.LoginDTO;

/**
 * Created By oscar.vergara on 22/08/2020
 */
public class Session {

    LoginDTO user;

    public LoginDTO getUser() {
        return user;
    }

    public void setUser(LoginDTO user) {
        this.user = user;
    }
}
