package com.d954mas.game.singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.OrderedMap;
import com.d954mas.game.EsqGame;
import com.d954mas.game.screens.LogoScreen;

public class Screens {


    public static Screens instance=new Screens();
    private  OrderedMap<Class<? extends Screen>,Screen> screens;
    private EsqGame game;
    private Screens(){

    }

    private void createScreens(){
        screens.put(LogoScreen.class,new LogoScreen());
    }

    public void dispose() {
        for(Screen screen:instance.screens.values()){
            screen.dispose();
        }
    }

    public void init(EsqGame game){
        if(instance.screens!=null){
            throw new RuntimeException("Screens must be initialize once");
        }
        instance.screens=new OrderedMap<>();
        instance.game=game;
        instance.createScreens();
    }


    public void setScreen(Class clazz){
        instance.game.setScreen(instance.screens.get(clazz));
    }

    public void setLoadingGroup(){
        Table loading=new Table();
        loading.setFillParent(true);
        //loading.setBackground(new ColorDra);
        loading.add().expand().fill();
        EsqGame.getStage().addActor(loading);
        while (!MathUtils.isEqual(Assets.instance.getManager().getProgress(),1f)){
            Assets.instance.getManager().update();
            Gdx.app.log("Screens","reloading openGL resources");
        }
    }

}
