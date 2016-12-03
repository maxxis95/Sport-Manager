package com.foi.air1603.sport_manager.loaders;

import com.example.webservice.AirWebServiceCaller;
import com.example.webservice.AirWebServiceHandler;
import com.example.webservice.AirWebServiceResponse;

import java.lang.reflect.Type;

/** Implementira funkcionalnosti za dohvaćanje podatak s web servisa
 * Created by Karlo on 9.11.2016..
 */

public class WsDataLoader extends DataLoader{

    /**
     * Implementira metodu apstraktne klase DataLoader koja služi za pozivanje podataka
     * @param dataLoadedListener Listner tipa DataLoadedListener na kojeg će se slati nazad podaci
     * @param method Metoda koja se poziva za dohvaćanje podataka
     * @param entityType Tip objekta koji se očekuje kao rezultat
     * @param data Podaci koji se šalju na spremanje
     */
    @Override
    public void loadData(DataLoadedListener dataLoadedListener, String method, String tableName, String searchBy, String value, Type entityType, Object data) {
        super.loadData(dataLoadedListener, method, tableName, searchBy, value, entityType, data);

        System.out.println("----------------->4. WsDataLoader:loadData");
        AirWebServiceCaller call = new AirWebServiceCaller(responseHandler);

        call.getData(method, tableName, searchBy, value, entityType, data);
    }

    /**
     * Instancira se novi objekt tipa AirWebServiceHandler i odmah se implementira metoda onDataArrived.
     * U metodu onDataArrived se vraćaju rezultati poziva web servisa koji je pozvan instanciranjem objekta ove klase (WsDataLoader)
     */
    AirWebServiceHandler responseHandler = new AirWebServiceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok) {
            System.out.println("----------------->6. WsDataLoader:responseHandler");
            if(ok){
                mDataLoadedListener.onDataLoaded((AirWebServiceResponse) result);
            }
        }
    };

}