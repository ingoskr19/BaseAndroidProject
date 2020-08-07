package co.com.android.baseproject.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {

}
