package com.mihtechno.sketch;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Appinterface {
    @POST("api/Account/Login")
    public Call<Accountdata> loginUser(@Body Login login);
    @Headers({"'appId':'0'","'lang':'1'","'Access-Control-Allow-Origin': '*'"})
    @POST("api/Mobile/SavePlayerID")
    public Call<TokenFirbase> gettoken(@Query("PlayerId")String PlayerId, @Header("Authorization")String Authorization);
}

/*
@Header("appId")String appId,
                                       @Header("lang") String lang,
                                       @Header("Access-Control-Allow-Origin")String Access_control,
                                       @Header("Authorization")String Authorization
 */