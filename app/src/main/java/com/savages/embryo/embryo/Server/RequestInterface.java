package com.savages.embryo.embryo.Server;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Asus on 04-02-2017.
 */
public interface RequestInterface {

    @POST("login/")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
