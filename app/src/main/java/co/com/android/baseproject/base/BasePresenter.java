package co.com.android.baseproject.base;

import javax.inject.Inject;

import co.com.android.baseproject.util.InternetValidator;

/**
 * Created By oscar.vergara on 6/08/2020
 */
abstract class BasePresenter {
    
    @Inject
    protected InternetValidator internetValidator;

    abstract void onGenericError();
}
