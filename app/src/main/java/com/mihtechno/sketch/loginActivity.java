package com.mihtechno.sketch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginActivity extends AppCompatActivity {
    private EditText useredt,passwordedt;
    private Button loginbtn;
    SharedPreferences sharedPreferences;
    private  static final String SHARED_PREF_NAME = "mypref";
    private  static final String SHAREDISLOGIN = "islogin";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        useredt  = (EditText) findViewById(R.id.edtusername);
        passwordedt = (EditText) findViewById(R.id.edtPassword);
        loginbtn =(Button)findViewById(R.id.btnlogin);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String islogin = sharedPreferences.getString(SHAREDISLOGIN,null);
        if(islogin !=null){
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login login   =  new Login(useredt.getText().toString().trim(),passwordedt.getText().toString().trim());
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://sketch-api.birdcloud.qa")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Appinterface appinterface = retrofit.create(Appinterface.class);
                Call<Accountdata> call = appinterface.loginUser(login);
                call.enqueue(new Callback<Accountdata>() {
                    @Override
                    public void onResponse(Call<Accountdata> call, Response<Accountdata> response) {
                        System.out.println(response.body().getMessage());
                        System.out.println(response.body().getData().getAuthToken());
                        if(response.body().isSuccess()==true){
                            sendToken("Bearer "+ response.body().getData().getAuthToken());
                        }else{
                            Toast.makeText(loginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Accountdata> call, Throwable t) {

                    }
                });

            }
        });


    }

    public void sendToken(String  auth){
        String tokenfirbase ;
//        tokenfirbase = String.valueOf(FirebaseMessaging.getInstance().getToken());
//        System.out.println("-----------------------------"+tokenfirbase);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if(!task.isSuccessful()){
                            System.out.println("Fetching FCM registration token failed" +task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Toast.makeText(loginActivity.this, token, Toast.LENGTH_SHORT).show();
                        System.out.println(token);
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://sketch-api.birdcloud.qa")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        Appinterface appinterface = retrofit.create(Appinterface.class);
                        Call<TokenFirbase>call =appinterface.gettoken(token,auth);
                        call.enqueue(new Callback<TokenFirbase>() {
                            @Override
                            public void onResponse(Call<TokenFirbase> call, Response<TokenFirbase> response) {
                                System.out.println(response.body().getSuccess());
                                System.out.println(response.body().getMessage());
                                System.out.println(response.code());
                                if(response.body().getSuccess() == "true"){
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(SHAREDISLOGIN,"true");
                                    editor.apply();
                                    Intent settingintent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    settingintent.setData(uri);

                                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    startActivity(settingintent);
                                }
                                else{
                                    Toast.makeText(loginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<TokenFirbase> call, Throwable t) {

                            }
                        });
                    }

                });


    }
}

/*
Call<TokenFirbase> tokenFirbaseCall = appinterface.gettoken("EEEEEEEEEEEEEEEEEEEEE",response.body().getData().getAuthToken());
                       tokenFirbaseCall.enqueue(new Callback<TokenFirbase>() {
                           @Override
                           public void onResponse(Call<TokenFirbase> call, Response<TokenFirbase> response) {
//                               System.out.println(response.body().getMessage());
                               System.out.println(response.code());
                           }

                           @Override
                           public void onFailure(Call<TokenFirbase> call, Throwable t) {

                           }
                       });
 */