package co.com.android.baseproject.screens.login.model;

import android.os.CountDownTimer;
import android.os.SystemClock;

import co.com.android.baseproject.entities.Login;
import co.com.android.baseproject.screens.login.ILogin;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public class LoginModel implements ILogin.Model {

    private ILogin.Callback mCallback;

    @Override
    public void setCallback(ILogin.Callback presenter) {
        this.mCallback = presenter;
    }

    @Override
    public void doLogin(Login login) {
        CountDownTimer timer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mCallback.onLoginSuccess();
            }
        };
        timer.start();
    }
}
