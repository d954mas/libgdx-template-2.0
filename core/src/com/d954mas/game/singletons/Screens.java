package com.d954mas.game.singletons;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.OrderedMap;
import com.d954mas.game.EsqGame;
import com.d954mas.game.screens.LoadingScreen;

public class Screens {
    public static final Screens instance=new Screens();


    private  OrderedMap<Class<? extends Screen>,Screen> screens;
    private EsqGame game;
    private Screens(){

    }

    private void createScreens(){
        screens.put(LoadingScreen.class,new LoadingScreen());
    }

    public void dispose() {
        for(Screen screen:instance.screens.values()){
            screen.dispose();
        }
    }

    public void preInit(EsqGame game){
        instance.screens=new OrderedMap<>();
        instance.game=game;
        LoadingScreen loadingScreen=new LoadingScreen();
        loadingScreen.getInnerEventModel().addListener("finish_loading",listener->{
            init();
            //remove listener later
            //set default screen menu or something like that
        });
        screens.put(LoadingScreen.class,loadingScreen);

    }

    private void init(){
        instance.createScreens();
    }


    public void setScreen(Class clazz){
        instance.game.setScreen(instance.screens.get(clazz));
    }
}
