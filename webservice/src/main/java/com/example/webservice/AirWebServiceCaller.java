package com.example.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Karlo on 9.11.2016..
 */

public class AirWebServiceCaller {

    AirWebServiceHandler mAirWebServiceHandler;
    Retrofit retrofit;

    //private final String baseUrl = "http://cortex.foi.hr/mtl/courses/air/";
    private final String baseUrl = "http://sportmanager.fitforev.lin25.host25.com/";

    public AirWebServiceCaller(AirWebServiceHandler airWebServiceHandler){
        this.mAirWebServiceHandler = airWebServiceHandler;

        //To verify what's sending over the network, use Interceptors
        OkHttpClient client = new OkHttpClient();

        // for debuggint use HttpInterceptor
        //client.interceptors().add(new HttpInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    /**
     * Metoda koja poziva web servis s definiranim prametrima
     * @param method Naziv metode koju servis podržava
     * @param args Argumenti koji s kojima se metoda ne web servisu poziva
     * @param entityType Klasa prema kojoj će GSON parsirati json
     */
    public void getData(String method, String args, final Type entityType){

        AirWebService serviceCaller = retrofit.create(AirWebService.class);
        Call<AirWebServiceResponse> call;
        call = serviceCaller.getData(method, "1");

        if(call != null){
            call.enqueue(new Callback<AirWebServiceResponse>() {
                @Override
                public void onResponse(Response<AirWebServiceResponse> response, Retrofit retrofit) {
                    try {
                        if(response.isSuccess()){
                            handleResponse(response);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }


    private void handleResponse(Response<AirWebServiceResponse> response) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd") // response JSON format
                .create();

        System.out.println(gson.toJson(response.body(), AirWebServiceResponse.class));

        User user = gson.fromJson(response.body().getData(), User.class);

        if(mAirWebServiceHandler != null){
            mAirWebServiceHandler.onDataArrived(user, true);
        }

    }
}
