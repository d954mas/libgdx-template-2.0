package com.d954mas.game.services;

import com.badlogic.gdx.utils.Disposable;

public interface Service extends Disposable{
    // is called on startup only once
    public void init();
}
