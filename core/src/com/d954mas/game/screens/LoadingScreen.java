package com.d954mas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.d954mas.game.screens.events.EsqEvent;
import com.d954mas.game.singletons.Assets;

import java.util.HashMap;
import java.util.Map;

public class LoadingScreen extends EsqScreen {
    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        while(!Assets.instance.getManager().update()){
            Gdx.app.log("LoadingScreen","loading assets");
            innerEventModel.fireEvent("finish_loading");
            Assets.instance.logAssetsInfo();
        }
    }

    @Override
    protected Map<String, Class<EsqEvent>> initInnerEventTypes() {
        Map<String, Class<EsqEvent>> map=new HashMap<>();
        map.put("finish_loading",null);
        return map;
    }
}
