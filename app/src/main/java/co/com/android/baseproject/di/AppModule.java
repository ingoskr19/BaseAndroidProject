package co.com.android.baseproject.di;

import android.app.Application;

import javax.inject.Singleton;

import co.com.android.baseproject.util.InternetValidator;
import co.com.android.baseproject.util.PreferencesUtil;
import dagger.Module;
import dagger.Provides;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Module
public abstract class AppModule {

    @Provides
    @Singleton
    public static PreferencesUtil getPreference(){
        return new PreferencesUtil();
    }

    @Provides
    @Singleton
    public static InternetValidator internetValidate(Application application) {
        return new InternetValidator(application.getApplicationContext());
    }

}
