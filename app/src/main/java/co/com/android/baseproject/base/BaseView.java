package co.com.android.baseproject.base;

import android.app.Activity;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public interface BaseView {
    void onGenericError(String msjError);
    Activity getActivity();
}
