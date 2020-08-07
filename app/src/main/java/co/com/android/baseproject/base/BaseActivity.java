package co.com.android.baseproject.base;

import android.app.Activity;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import javax.inject.Inject;

import co.com.android.baseproject.R;
import co.com.android.baseproject.util.InternetValidator;
import co.com.android.baseproject.util.PreferencesUtil;
import dagger.android.support.DaggerAppCompatActivity;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public abstract class BaseActivity extends DaggerAppCompatActivity implements BaseView {

    @Inject
    protected PreferencesUtil preferences;

    @Inject
    protected InternetValidator internetValidator;

    protected View loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoading(String msj){
        ViewGroup root = findViewById(R.id.root);
        loading = getLayoutInflater().inflate(R.layout.progress_bar, root, false);
        TextView loadingText = loading.findViewById(R.id.progressbar_text);
        loadingText.setText(msj);
        root.removeView(loading);
        root.addView(loading);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void removeLoading(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        ConstraintLayout root = findViewById(R.id.root);
        root.removeView(loading);
    }

    protected void showLoading2(String msj){
        loading = getLayoutInflater().inflate(R.layout.progress_bar,null);
        TextView loadingText = loading.findViewById(R.id.progressbar_text);
        loadingText.setText(msj);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setView(loading);

        AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            //dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = MATCH_PARENT;
            layoutParams.height = MATCH_PARENT;
            dialog.getWindow().setAttributes(layoutParams);
        }
        dialog.show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
