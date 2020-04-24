package com.mlclassifier.ecgclaissfier.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.mlclassifier.ecgclaissfier.BaseActivity;
import com.mlclassifier.ecgclaissfier.MainActivity;
import com.mlclassifier.ecgclaissfier.R;
import com.mlclassifier.ecgclaissfier.model.User;
import com.mlclassifier.ecgclaissfier.ui.registration.RegistrationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    private LoginViewModel loginViewModel;
    private Unbinder unbinder;

    @BindView(R.id.username)
    EditText userName;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.registrationRoute)
    TextView registrationRoute;

    @BindView(R.id.loginSubmit)
    Button loginSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(unbinder == null){
            unbinder = ButterKnife.bind(this);
        }
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
//        if(loginViewModel.logingIn()){
//            if(loginViewModel.entity.getLoggedIn()){
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra(getString(R.string.txt_entity), loginViewModel.entity);
//                startActivity(intent);
//                finish();
//            }else {
//                this.userName.setText(loginViewModel.entity.getUserName());
//                Log.d(TAG, "onCreate: "+ loginViewModel.entity.getPassword());
//                Log.d(TAG, "onCreate: "+ loginViewModel.entity.getLoggedIn());
//                this.userName.setEnabled(false);
//
//            }
//        }



    }





    @OnClick(R.id.registrationRoute)
    public void RegistrationRoute(){
        startActivity(new Intent(this, RegistrationActivity.class));
    }



    @OnClick(R.id.loginSubmit)
    public void login(){
        String userName, password;
        userName = this.userName.getText().toString();
        password = this.password.getText().toString();
        if(userName.isEmpty()){
            this.userName.setError(getString(R.string.txt_username_error));
            this.userName.requestFocus();
        }else if(password.isEmpty()){
            this.password.setError(getString(R.string.txt_password_error));
            this.password.requestFocus();
//        }else if(!checkPasswordType(password)){
//            this.password.setError(getString(R.string.txt_password_error_length));
//            this.password.requestFocus();
        } else{
            showProgressDialogue(getString(R.string.loading), getString(R.string.txt_login_loading));
            User usser = new User();
            usser.setEmail(userName);
            usser.setPassword(password);
//            if(loginViewModel.setUser(userName, password)){
                Intent intent =  new Intent(this, MainActivity.class);
                intent.putExtra(getString(R.string.txt_entity),usser);
                startActivity(intent);
                dissmissProgressDialogue();
                finish();
//            }else {
//                dissmissProgressDialogue();
//                showErrorSnackBar(getString(R.string.txt_wrong_password));
//            }

        }

    }
}
