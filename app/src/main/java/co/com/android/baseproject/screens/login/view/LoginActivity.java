package co.com.android.baseproject.screens.login.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import co.com.android.baseproject.R;
import co.com.android.baseproject.base.BaseActivity;
import co.com.android.baseproject.databinding.LoginActivityBinding;
import co.com.android.baseproject.screens.login.model.Login;
import co.com.android.baseproject.screens.login.viewmodel.LoginViewModel;
import co.com.android.baseproject.util.CustomFormats;
import dagger.hilt.android.AndroidEntryPoint;

import static co.com.android.baseproject.util.Constants.EMAIL_MAX_LENGTH;
import static co.com.android.baseproject.util.Constants.PASSWORD_LENGTH;

/**
 * Created By oscar.vergara on 6/08/2020
 */
@AndroidEntryPoint
public class LoginActivity extends BaseActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener {

    public LoginViewModel mVieModel;
    private LoginActivityBinding binding;
    private Login dataSaved = new Login();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mVieModel = new ViewModelProvider(this).get(LoginViewModel.class);

        verifyPreference();
        validateInputs();
        setListeners();
    }

    private void validateInputs() {
        Login login = getDataForLogin();
        binding.loginBtnLogin.setEnabled(validateInputs(login.getEmail(),login.getPassword()));
    }

    private void verifyPreference() {
        mVieModel.getDataSaved().observe(this,loginStateData -> {
           removeLoading();
            switch (loginStateData.getStatus()){
               case LOADING:
                   showLoading(getString(R.string.loading_message));
                   break;
               case SUCCESS:
                   this.dataSaved = loginStateData.getData();
                   binding.loginRememberMeCb.setChecked(dataSaved.isRememberMe());
                   binding.loginEmailEt.setText(CustomFormats.emailMask(dataSaved.getEmail()));
                   binding.loginPasswordEt.setText(dataSaved.getPassword());
                   binding.loginEmailEt.setEnabled(!dataSaved.isRememberMe());
                   binding.loginPasswordEt.setEnabled(!dataSaved.isRememberMe());
                   binding.loginBtnLogin.setEnabled(validateInputs(dataSaved.getEmail(),dataSaved.getPassword()));
                   break;
               case ERROR:
                   Toast.makeText(this,loginStateData.getError().getMessage(),Toast.LENGTH_SHORT).show();
                   break;
                default:
                    break;
           }
        });
        mVieModel.getInfoSaved();
    }

    private Login getDataForLogin() {
        if(dataSaved.isRememberMe()){
            return dataSaved;
        }
        Login login = new Login();
        login.setEmail(binding.loginEmailEt.getText().toString());
        login.setPassword(binding.loginPasswordEt.getText().toString());
        login.setRememberMe(binding.loginRememberMeCb.isChecked());
        return login;
    }

    private void setListeners() {
        binding.loginEmailEt.addTextChangedListener(this);
        binding.loginPasswordEt.addTextChangedListener(this);
        binding.loginRememberMeCb.setOnCheckedChangeListener(this);
        binding.loginBtnLogin.setOnClickListener(this::onLoginClickBtn);
    }

    private void onLoginClickBtn(View view){
        mVieModel.getLogin().observe(this,loginDTOStateData -> {
            switch (loginDTOStateData.getStatus()){
                case LOADING:
                    showLoading(getString(R.string.loading_message));
                    break;
                case SUCCESS:
                    this.session.setUser(loginDTOStateData.getData());
                    break;
                case ERROR:
                    Toast.makeText(this,loginDTOStateData.getError().getMessage(),Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        });
        Login login = getDataForLogin();
        mVieModel.doLogin(login);
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
            binding.loginEmailEt.setEnabled(true);
            binding.loginEmailEt.setText("");
            binding.loginPasswordEt.setEnabled(true);
            binding.loginPasswordEt.setText("");
            dataSaved.setRememberMe(false);
        }
    }

    private boolean validateInputs(String email, String password) {
        boolean isValid = !TextUtils.isEmpty(email)
                && email.length() <= EMAIL_MAX_LENGTH
                &&  CustomFormats.emailMatcher(email);
        if(isValid){
            isValid = !TextUtils.isEmpty(password) && password.length() >= PASSWORD_LENGTH;
        }
        return isValid;
    }
}
