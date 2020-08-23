package co.com.android.baseproject.screens.login.module;

import android.app.Application;

import co.com.android.baseproject.BuildConfig;
import co.com.android.baseproject.screens.login.ILogin;
import co.com.android.baseproject.screens.login.data.LoginDataSource;
import co.com.android.baseproject.screens.login.data.preference.LoginPreferences;
import co.com.android.baseproject.screens.login.data.service.LoginApiService;
import co.com.android.baseproject.screens.login.interactor.LoginInteractor;
import co.com.android.baseproject.screens.login.view.LoginActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Module
@InstallIn(ActivityRetainedComponent.class)
public abstract class LoginModule {

    @Binds
    @ActivityRetainedScoped
    abstract LoginActivity loginActivity(LoginActivity loginActivity);

    @Provides
    @ActivityRetainedScoped
    static LoginPreferences preferences(Application context){
        return new LoginPreferences(context);
    }

    @Provides
    @ActivityRetainedScoped
    static LoginApiService api(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.LOGIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(LoginApiService.class);
    }

    @Provides
    @ActivityRetainedScoped
    static ILogin.Repository repository(LoginPreferences preferences, LoginApiService api){
        return new LoginDataSource(preferences, api);
    }

    @Provides
    @ActivityRetainedScoped
    static ILogin.Intectaror loginInteractor(ILogin.Repository repository){
        return new LoginInteractor(repository);
    }

}
