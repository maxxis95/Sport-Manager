package com.example.webservice;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Karlo on 9.11.2016..
 */

public interface AirWebService {

    @FormUrlEncoded
    @POST("/")
    Call<AirWebServiceResponse> getData(@Field("method") String method, @Field("args") String args, @Field("data") String data);

}
