package co.com.android.baseproject.di;

import co.com.android.baseproject.di.scopes.PerActivity;
import co.com.android.baseproject.screens.login.module.LoginModule;
import co.com.android.baseproject.screens.login.view.LoginActivity;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Module(includes = AndroidInjectionModule.class)
public interface ActivityModule {
//provide activity's module's instance

    @PerActivity
    @ContributesAndroidInjector(modules = {LoginModule.class})
    LoginActivity loginActivityInjector();
}
