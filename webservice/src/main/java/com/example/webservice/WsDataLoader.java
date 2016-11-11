package com.example.webservice;

import java.lang.reflect.Type;

/**
 * Created by Karlo on 9.11.2016..
 */

public class WsDataLoader extends DataLoader{

    @Override
    public void callWebService(DataLoadedListener dataLoadedListener, String method, String args, Type entityType, Object data) {
        super.callWebService(dataLoadedListener, method, args, entityType, data);

        System.out.println("----------------->4. WsDataLoader:callWebService");
        AirWebServiceCaller call = new AirWebServiceCaller(responseHandler);

        call.getData(method, args, entityType, data);
    }

    AirWebServiceHandler responseHandler = new AirWebServiceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok) {
            System.out.println("----------------->6. WsDataLoader:responseHandler");
            if(ok){
                mDataLoadedListener.onDataLoaded(result);
            }
        }
    };

}