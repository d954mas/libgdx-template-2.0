package com.d954mas.game.services;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.OrderedMap;

public class Services {
    private static Services instance;
    private OrderedMap<Class<? extends Service>, Service> realServicesMap;

    private Services(){
        realServicesMap=new OrderedMap<>();
    }

    private static Services getInstance(){
        if(instance == null){
            instance = new Services();
        }
        return instance;
    }

    public static void init(){
        //init all services
        for(Service service:instance.realServicesMap.values()){
            service.init();
        }
    }

    public static void addOrReplaceService(Class<? extends Service> iface, Service service) {
        getInstance().realServicesMap.put(iface, service);
       // service.init();
    }
    public static void addOrReplaceServices(OrderedMap<Class<? extends Service>, Service> map){
        for(Class<? extends Service> key:map.keys()){
            addOrReplaceService(key,map.get(key));
        }
    }

    public static  <T extends Service> T get(Class<T> iface) {
        return (T) getInstance().realServicesMap.get(iface);
    }


    public static void dispose() {
        for(Service service:getInstance().realServicesMap.values()){
            service.dispose();
        }
    }
}
