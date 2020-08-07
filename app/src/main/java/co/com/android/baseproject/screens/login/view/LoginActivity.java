package co.com.android.baseproject.screens.login.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import co.com.android.baseproject.R;
import co.com.android.baseproject.base.BaseActivity;
import co.com.android.baseproject.databinding.LoginActivityBinding;
import co.com.android.baseproject.entities.Login;
import co.com.android.baseproject.screens.login.ILogin;
import co.com.android.baseproject.screens.login.presenter.LoginPresenter;
import co.com.android.baseproject.util.CustomFormats;
import co.com.android.baseproject.util.PreferencesUtil;

import static co.com.android.baseproject.util.Constants.REMEMBER_EMAIL;
import static co.com.android.baseproject.util.Constants.REMEMBER_EMAIL_VALUE;
import static co.com.android.baseproject.util.Constants.REMEMBER_PASS;
import static co.com.android.baseproject.util.Constants.REMEMBER_PASS_VALUE;

/**
 * Created By oscar.vergara on 6/08/2020
 */
public class LoginActivity extends BaseActivity implements ILogin.View, View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

    @Inject
    public LoginPresenter mPresenter;

    private LoginActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        verifyPreference();
        validateInputs();
        setListeners();
    }

    private void validateInputs() {
        Login login = getDataForLogin();
        binding.loginBtnLogin.setEnabled(mPresenter.validateInputs(login.getEmail(),login.getPassword()));
    }

    private void verifyPreference() {
        binding.loginRememberEmailCb.setChecked((boolean)PreferencesUtil.getPreference(this, REMEMBER_EMAIL,false));
        binding.loginRememberPassCb.setChecked((boolean)PreferencesUtil.getPreference(this,REMEMBER_PASS,false));
        if(binding.loginRememberEmailCb.isChecked()) {
            binding.loginEmailEt.setHint((String)PreferencesUtil.getPreference(this, REMEMBER_EMAIL_VALUE,""));
            binding.loginEmailEt.setText(CustomFormats.emailMask((String)PreferencesUtil.getPreference(this, REMEMBER_EMAIL_VALUE,"")));
            binding.loginEmailEt.setEnabled(false);
        }
        if(binding.loginRememberPassCb.isChecked()) {
            binding.loginPasswordEt.setHint((String)PreferencesUtil.getPreference(this,REMEMBER_PASS_VALUE,""));
            binding.loginPasswordEt.setText((String)PreferencesUtil.getPreference(this,REMEMBER_PASS_VALUE,""));
            binding.loginPasswordEt.setEnabled(false);
        }
    }

    private void removePreference() {
        PreferencesUtil.removePreference(this, REMEMBER_EMAIL);
        PreferencesUtil.removePreference(this, REMEMBER_EMAIL_VALUE);
        PreferencesUtil.removePreference(this, REMEMBER_PASS);
        PreferencesUtil.removePreference(this, REMEMBER_PASS_VALUE);
        binding.loginEmailEt.setEnabled(true);
        binding.loginEmailEt.setText("");
        binding.loginPasswordEt.setEnabled(true);
        binding.loginPasswordEt.setText("");
    }

    private void savePreference() {
        if(binding.loginRememberEmailCb.isChecked() && binding.loginEmailEt.isEnabled()) {
            PreferencesUtil.setPreference(this, REMEMBER_EMAIL,binding.loginRememberEmailCb.isChecked());
            PreferencesUtil.setPreference(this, REMEMBER_EMAIL_VALUE,binding.loginEmailEt.getText().toString());
        }

        if(binding.loginRememberPassCb.isChecked() && binding.loginPasswordEt.isEnabled()) {
            PreferencesUtil.setPreference(this,REMEMBER_PASS,binding.loginRememberPassCb.isChecked());
            PreferencesUtil.setPreference(this,REMEMBER_PASS_VALUE,binding.loginPasswordEt.getText().toString());
        }
    }

    private Login getDataForLogin() {
        Login login = new Login();
        login.setEmail(binding.loginEmailEt.getText().toString());
        login.setPassword(binding.loginPasswordEt.getText().toString());

        if(binding.loginRememberEmailCb.isChecked() && !binding.loginEmailEt.isEnabled()) {
            login.setEmail((String) PreferencesUtil.getPreference(this, REMEMBER_EMAIL_VALUE, ""));
        }
        if(binding.loginRememberPassCb.isChecked() && !binding.loginPasswordEt.isEnabled()) {
            login.setPassword((String) PreferencesUtil.getPreference(this, REMEMBER_PASS_VALUE, ""));
        }

        return login;
    }

    private void setListeners() {
        binding.loginEmailEt.addTextChangedListener(this);
        binding.loginPasswordEt.addTextChangedListener(this);

        binding.loginRememberEmailCb.setOnCheckedChangeListener(this);
        binding.loginRememberPassCb.setOnCheckedChangeListener(this);

        binding.loginBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onGenericError(String msjError) {
        //No necessary
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_login:
                onClickLogin();
                break;
            default:
                break;
        }
    }

    private void onClickLogin(){
        Login login = getDataForLogin();
        savePreference();
        showLoading(getString(R.string.loading_message));
        mPresenter.doLogin(login);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Not implemented
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Not implemented
    }

    @Override
    public void afterTextChanged(Editable s) {
        validateInputs();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(!isChecked){
            removePreference();
        }
    }

    @Override
    public void onLoginSuccess() {
        removeLoading();
    }
}
