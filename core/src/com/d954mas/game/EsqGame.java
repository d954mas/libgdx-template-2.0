package com.d954mas.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.OrderedMap;
import com.d954mas.game.screens.LogoScreen;
import com.d954mas.game.services.Service;
import com.d954mas.game.services.Services;
import com.d954mas.game.singletons.Assets;
import com.d954mas.game.singletons.Screens;
import com.d954mas.game.utils.PerfomanceLogger;

//Игра принимает мапу стандартных сервисов
//для каждой платформы там может быть специфический код

public class EsqGame extends Game {

    private static Stage stage;
    private PerfomanceLogger perfomanceLogger;

    public EsqGame(OrderedMap<Class<? extends Service>, Service> nativeServices) {
        super();
        Services.addOrReplaceServices(nativeServices);

    }

    public static Stage getStage(){
        return stage;
    }


    @Override
    public void create() {
        Gdx.app.debug(this.getClass().getSimpleName(), "create");
        Services.init();
        Assets.instance.init(new AssetManager());
        Assets.instance.preLoad();
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        perfomanceLogger=new PerfomanceLogger();
        Screens.instance.init(this);
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Screens.instance.setScreen(LogoScreen.class);
        Gdx.app.log("","");
       // List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
      //  numbers.forEach(value -> System.out.println(value));
    }

    @Override
    public void render() {
        perfomanceLogger.update(Gdx.graphics.getDeltaTime());
        super.render();
    }

    @Override
    public void pause() {
        Gdx.app.debug(this.getClass().getSimpleName(),"pause");
        super.pause();
    }

    @Override
    public void resume() {
        Gdx.app.debug(this.getClass().getSimpleName(),"resume");
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug(this.getClass().getSimpleName(),"resize("+width+" "+height+")");
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        Gdx.app.log("EsqGame","dispose");
        Gdx.app.log("EsqGame","---------------");
        Gdx.app.log(this.getClass().getSimpleName(),"dispose");
        Services.dispose();
        Screens.instance.dispose();
        Assets.instance.dispose();
        Gdx.app.log("EsqGame", "---------------");
    }
}
