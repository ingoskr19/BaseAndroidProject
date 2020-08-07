package co.com.android.baseproject.util;

import android.text.TextUtils;

import androidx.annotation.Nullable;

/**
 * Created By oscar.vergara on 8/08/2020
 */
public class CustomFormats {

    public static boolean emailMatcher(String target){
        return (!TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static String emailMask(@Nullable String email){
        if(!TextUtils.isEmpty(email)) {
            int dotPosition = email.lastIndexOf(".");
            return email.substring(0, 3).concat("***@***").concat(email.substring(dotPosition));
        }
        return "";
    }
}
