package co.com.android.baseproject.screens.login.presenter;

import android.text.TextUtils;

import javax.inject.Inject;

import co.com.android.baseproject.entities.Login;
import co.com.android.baseproject.screens.login.ILogin;
import co.com.android.baseproject.util.CustomFormats;

import static co.com.android.baseproject.util.Constants.EMAIL_MAX_LENGTH;
import static co.com.android.baseproject.util.Constants.PASSWORD_LENGTH;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public class LoginPresenter implements ILogin.Presenter, ILogin.Callback {

    private ILogin.View mView;
    private ILogin.Model mModel;

    @Inject
    public LoginPresenter(ILogin.View pView, ILogin.Model pModel) {
        this.mView = pView;
        this.mModel = pModel;
        this.mModel.setCallback(this);
    }

    @Override
    public void doLogin(Login login) {
        mModel.doLogin(login);
    }

    @Override
    public boolean validateInputs(String email, String password) {
        boolean isValid = !TextUtils.isEmpty(email)
                && email.length() <= EMAIL_MAX_LENGTH
                &&  CustomFormats.emailMatcher(email);
        if(isValid){
            isValid = !TextUtils.isEmpty(password) && password.length() >= PASSWORD_LENGTH;
        }
        return isValid;
    }

    @Override
    public void onLoginSuccess() {
        mView.onLoginSuccess();
    }

    @Override
    public void errorEvent(String message) {
        mView.onLoginSuccess();
    }
}
