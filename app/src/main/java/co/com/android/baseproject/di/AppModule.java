package co.com.android.baseproject.di;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.com.android.baseproject.session.Session;
import co.com.android.baseproject.util.Constants;
import co.com.android.baseproject.util.InternetValidator;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Module
@InstallIn(ApplicationComponent.class)
public abstract class AppModule {

    @Provides
    @Singleton
    static Session provideSession(){
        return new Session();
    }

    @Provides
    @Singleton
    static InternetValidator provideInternetValidator(Application application) {
        return new InternetValidator(application.getApplicationContext());
    }

    @Provides
    @Singleton
    static HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

}
