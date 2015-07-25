package com.d954mas.game.utils;

import com.badlogic.gdx.Gdx;

public class PerfomanceLogger {
    public static final float MAX_TIME=60f;
    private float time;
    public void update(float delta){
        time+=delta;
        if(time>MAX_TIME){
            time-=MAX_TIME;
            Gdx.app.debug("PerfomanceLogger","fps:"+Gdx.graphics.getFramesPerSecond());
            float javaHeap = Gdx.app.getJavaHeap()/1048576f;
            float nativeHeap = Gdx.app.getNativeHeap()/1048576f;
            Gdx.app.debug("PerfomanceLogger","javaHeap:"+javaHeap);
            Gdx.app.debug("PerfomanceLogger","nativeHeap:"+nativeHeap);
        }
    }
}
