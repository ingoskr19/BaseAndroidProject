package co.com.android.baseproject.di;

import android.app.Activity;

import co.com.android.baseproject.base.BaseActivity;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * Created By oscar.vergara on 21/08/2020
 */
@Module
@InstallIn(ActivityComponent.class)
public class BaseActivityModule {

    @Provides
    static BaseActivity provideBaseActivity(Activity activity) {
        return (BaseActivity) activity;
    }
}
