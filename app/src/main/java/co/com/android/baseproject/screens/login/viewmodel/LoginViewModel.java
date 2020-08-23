package co.com.android.baseproject.screens.login.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import co.com.android.baseproject.base.state.StateLiveData;
import co.com.android.baseproject.screens.login.ILogin;
import co.com.android.baseproject.screens.login.model.Login;
import co.com.android.baseproject.screens.login.model.LoginDTO;
import co.com.android.baseproject.util.Constants;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/**
 * Created By oscar.vergara on 6/08/2020
 */
public class LoginViewModel extends ViewModel implements ILogin.ViewModel {

    private ILogin.Intectaror intectaror;
    private StateLiveData<LoginDTO> login = new StateLiveData<>();
    private StateLiveData<Login> infoSaved = new StateLiveData<>();

    @ViewModelInject
    public LoginViewModel(ILogin.Intectaror intectaror){
        this.intectaror = intectaror;
    }

    @Override
    public void getInfoSaved(){
        intectaror.getLocalInfo(Constants.LOGIN_NAME)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<Login>() {
            @Override
            public void onSubscribe(Disposable d) {
                infoSaved.postLoading();
            }

            @Override
            public void onSuccess(Login login) {
                infoSaved.postSuccess(login);
            }

            @Override
            public void onError(Throwable e) {
                infoSaved.postError(e);
            }
        });
    }

    @Override
    public void doLogin(Login l) {
        intectaror.login(l)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<LoginDTO>() {
            @Override
            public void onSubscribe(Disposable d) {
                login.postLoading();
            }

            @Override
            public void onSuccess(LoginDTO loginDTO) {
                login.postSuccess(loginDTO);
            }

            @Override
            public void onError(Throwable e) {
                login.postError(e);
                Log.e("ViewModel","Error doLogin()",e);
            }
        });
    }

    public StateLiveData<Login> getDataSaved() {
        return infoSaved;
    }

    public StateLiveData<LoginDTO> getLogin() {
        return login;
    }
}
