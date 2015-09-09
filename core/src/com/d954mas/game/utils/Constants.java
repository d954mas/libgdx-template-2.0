package com.d954mas.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.d954mas.game.singletons.Assets;

public class Constants {
    public static UITextures uiTextures;


    public void initAssetsConstants(){
        uiTextures=new UITextures();
    }




    public static class UITextures{
        public TextureRegion empty;
        public UITextures(){
            TextureAtlas atlas= Assets.instance.getManager().get("ui/uiskin.atlas");
            empty=atlas.findRegion("empry");
        }
    }
}
