package com.example.webservice;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by Karlo on 24.12.2016..
 */

public interface PicUploadInterface {


    @Multipart
    @POST("picture-upload")
    Call<AirWebServiceResponse> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name);

}
