package co.com.android.baseproject.screens.login.module;

import co.com.android.baseproject.di.scopes.PerActivity;
import co.com.android.baseproject.screens.login.ILogin;
import co.com.android.baseproject.screens.login.model.LoginModel;
import co.com.android.baseproject.screens.login.presenter.LoginPresenter;
import co.com.android.baseproject.screens.login.view.LoginActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Module
public abstract class LoginModule {

    @Binds
    @PerActivity
    abstract ILogin.View view(LoginActivity loginActivity);

    @Binds
    @PerActivity
    abstract ILogin.Presenter presenter(LoginPresenter loginPresenter);

    @Provides
    @PerActivity
    public static ILogin.Model model(){
        return new LoginModel();
    }
}
