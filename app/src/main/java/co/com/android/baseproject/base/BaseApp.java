package co.com.android.baseproject.base;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import co.com.android.baseproject.di.DaggerAppComponent;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public class BaseApp extends Application implements HasActivityInjector {

    @Inject
    protected DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }
}
