package co.com.android.baseproject.screens.login.model;

/**
 * Created By oscar.vergara on 22/08/2020
 */
public class Login {

    private boolean rememberMe;
    private String email;
    private String password;

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
