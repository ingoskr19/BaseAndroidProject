package co.com.android.baseproject.di;

import android.app.Application;

import javax.inject.Singleton;

import co.com.android.baseproject.base.BaseApp;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApp app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
