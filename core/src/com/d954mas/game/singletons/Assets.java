package com.d954mas.game.singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.d954mas.game.utils.GeneratedFontSkinLoader;

import java.util.HashMap;
import java.util.Map;

public class Assets implements AssetErrorListener {
    public static final Assets instance = new Assets();
    private AssetManager manager;
    private Assets(){
    }

    public AssetManager getManager() {
        return manager;
    }

    public void init(AssetManager manager){
        Gdx.app.log("Assets","init");
        this.manager=manager;
        manager.setErrorListener(this);
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(resolver));
        Texture.setAssetManager(manager);
    }

    public void preLoad(){
        SkinLoader ldr = new GeneratedFontSkinLoader( new InternalFileHandleResolver(), instance.initFonts());
        instance.manager.setLoader(Skin.class, ldr);
        instance.manager.load("ui/uiskin.json", Skin.class);
        instance.manager.finishLoading();
        Gdx.app.log("Assets","---------------");
        Gdx.app.log("Assets","preLoad");
        logAssetsInfo();
        Gdx.app.log("Assets","---------------");


        //add other loading here
        manager.load("data/game.atlas", TextureAtlas.class);
    }

    protected Map<String,BitmapFont> initFonts() {
        Gdx.app.debug("GeneratedFontSkinLoader", "Loading fonts...");
        FileHandle fontFile = Gdx.files.internal("ui/default-font.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        Map<String,BitmapFont> fontsByName = new HashMap<>();
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        float ppi = Gdx.graphics.getPpiY();
        param.size = Math.round( ppi / 2);
        fontsByName.put("huge-font", generator.generateFont(param));
        param.size = Math.round( ppi / 3);
        fontsByName.put("big-font", generator.generateFont(param));
        param.size = Math.round( ppi / 4);
        fontsByName.put("default-font", generator.generateFont(param));
        generator.dispose();
        return fontsByName;
    }


    public void loadAll(){
        Gdx.app.log("Assets","---------------");
        Gdx.app.log("Assets","preLoad");
        //add Loading here
        logAssetsInfo();
        Gdx.app.log("Assets","---------------");

    }

    public void dispose() {
        instance.manager.dispose();
    }

    public void logAssetsInfo() {
        Gdx.app.log("Assets", "Assets loaded: "
                + manager.getAssetNames().size);
        for (String a : manager.getAssetNames()) Gdx.app.log("Assets", "asset: " + a);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error("Assets", "Could not load asset " + asset.fileName + " ", throwable);
    }
}
