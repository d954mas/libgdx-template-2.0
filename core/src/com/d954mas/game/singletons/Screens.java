package com.d954mas.game.singletons;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.OrderedMap;
import com.d954mas.game.MyGame;
import com.d954mas.game.screens.LogoScreen;

public class Screens {


    private static Screens instance;
    private  OrderedMap<Class<? extends Screen>,Screen> screens;
    private MyGame game;
    private Screens(){

    }

    private void createScreens(){
        screens.put(LogoScreen.class,new LogoScreen());
    }

    public static void dispose() {
        for(Screen screen:instance.screens.values()){
            screen.hide();
            screen.pause();
            screen.dispose();
        }
    }

    public static void init(MyGame game){
        if(instance!=null){
            throw new RuntimeException("Screens must be initialize once");
        }
        instance=new Screens();
        instance.screens=new OrderedMap<>();
        instance.game=game;
        instance.createScreens();
    }



    public static void setScreen(Class clazz){
        instance.game.setScreen(instance.screens.get(clazz));
    }

}
