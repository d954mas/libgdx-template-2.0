package com.d954mas.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.d954mas.game.screens.events.EsqEvent;

import java.util.HashMap;
import java.util.Map;

public class LogoScreen extends EsqScreen {
    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    protected Map<String, Class<EsqEvent>> initInnerEventTypes() {
        Map<String, Class<EsqEvent>> map=new HashMap<>();
        map.put("LogoFinished",null);
        return map;
    }
}
