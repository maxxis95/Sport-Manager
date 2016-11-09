package com.example.webservice;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Karlo on 9.11.2016..
 */

public class WsDataLoader extends DataLoader{

    @Override
    public void loadData(DataLoadedListener dataLoadedListener, String method, String args, Type entityType) {
        super.loadData(dataLoadedListener, method, args, entityType);

        AirWebServiceCaller demoCall = new AirWebServiceCaller(responseHandler);

        demoCall.getData(method, args, entityType);
    }

    AirWebServiceHandler responseHandler = new AirWebServiceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok) {
            if(ok){
                mDataLoadedListener.onDataLoaded((User) result);
            }
        }
    };

}
