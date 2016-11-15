package com.example.webservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONObject;

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

    private final String baseUrl = "http://sportmanager.fitforev.lin25.host25.com/";

    /**
     * @param airWebServiceHandler Handler kojeg se poziva nakon što dođu podaci s web servisa
     */
    public AirWebServiceCaller(AirWebServiceHandler airWebServiceHandler){
        this.mAirWebServiceHandler = airWebServiceHandler;

        OkHttpClient client = new OkHttpClient();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    /**
     * Metoda koja poziva web servis preko retrofita s definiranim prametrima
     * @param method Naziv metode koju servis podržava
     * @param args Argumenti koji s kojima se metoda ne web servisu poziva
     * @param entityType Klasa prema kojoj će GSON parsirati json
     * @param data Podaci koji se šalju web servisu kroz get parametar npr. objekt tipa User
     */
    public void getData(String method, String args, final Type entityType, Object data){

        AirWebService serviceCaller = retrofit.create(AirWebService.class);
        Call<AirWebServiceResponse> call;
        Gson gson = new Gson();

        if(data != null){
            call = serviceCaller.getData(method, args, gson.toJson(data, entityType));
        } else {
            call = serviceCaller.getData(method, args, null);
        }

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


    /**
     * Metoda koju se poziva nakon što Retrofit vrati inicijalni odgovor
     * @param response tipa AirWebServiceResponse kojeg vraća Retrofit
     */
    private void handleResponse(Response<AirWebServiceResponse> response) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd") // response JSON format
                .create();

        System.out.println("----------------->5. AirWebServiceCaller:handleResponse");
        System.out.println(gson.toJson(response.body(), AirWebServiceResponse.class));

        if(mAirWebServiceHandler != null){
            mAirWebServiceHandler.onDataArrived(response.body(), true);
        }

    }
}
