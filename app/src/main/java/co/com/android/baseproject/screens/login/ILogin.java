package co.com.android.baseproject.screens.login;

import co.com.android.baseproject.base.BaseCallback;
import co.com.android.baseproject.base.BaseView;
import co.com.android.baseproject.entities.Login;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public interface ILogin {

    interface View extends BaseView {
        void onLoginSuccess();
    }

    interface Presenter {
        void doLogin(Login login);
        boolean validateInputs(String email, String password);
    }

    interface Callback extends BaseCallback {
        void onLoginSuccess();
    }

    interface Model {
        void setCallback(ILogin.Callback presenter);
        void doLogin(Login login);
    }
}
