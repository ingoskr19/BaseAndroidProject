package co.com.android.baseproject.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Scope
@Singleton
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
