package co.com.android.baseproject.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created By oscar.vergara on 9/08/2020
 */
public class InternetValidator {

    private Context context;

    public InternetValidator(Context context) {
        this.context = context;
    }

    public boolean isConnected(){
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                return isConnectedOldVersion(cm);
            } else {
                final Network n = cm.getActiveNetwork();
                if (n != null && cm.getNetworkCapabilities(n) != null) {
                    return (Objects.requireNonNull(cm.getNetworkCapabilities(n)).hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || Objects.requireNonNull(cm.getNetworkCapabilities(n)).hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }
        return false;
    }

    @SuppressWarnings( "deprecation" )
    private boolean isConnectedOldVersion(ConnectivityManager cm){
        NetworkInfo ni = cm.getActiveNetworkInfo();
        List<Integer> types = Arrays.asList(ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_VPN);
        if(ni == null)
            return false;
        return (ni.isConnected() && types.contains(ni.getType()));
    }
}
