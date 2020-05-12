package com.mlclassifier.ecgclaissfier.repository;

import android.content.Intent;
import android.util.Log;

import com.mlclassifier.ecgclaissfier.MainActivity;
import com.mlclassifier.ecgclaissfier.model.Success;
import com.mlclassifier.ecgclaissfier.model.User;
import com.mlclassifier.ecgclaissfier.network_calls.RestApi;
import com.mlclassifier.ecgclaissfier.network_calls.RestApiInterface;
import com.mlclassifier.ecgclaissfier.ui.login.LoginActivity;
import com.mlclassifier.ecgclaissfier.ui.registration.RegistrationActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private static final String TAG = "RegistrationRepository";

    public void loginUser(String email, String pass, LoginActivity login){
        RestApiInterface service = RestApi.getApi();
        Call<Success> successCall = service.login(pass, email);

        successCall.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                if(response.isSuccessful()){
                    login.dissmissProgressDialogue();
                    login.showSuccessSnackBar(response.body().getSuccess());
                    Intent intent = new Intent(login, MainActivity.class);
                    login.startActivity(intent);

                }else {
                    login.dissmissProgressDialogue();
                    login.showErrorSnackBar("Login failed" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                login.dissmissProgressDialogue();
                login.showErrorSnackBar("Login failed" + t.getMessage());
                Log.d(TAG, "onFailure: "+ t.getMessage());

            }
        });

    }


}
