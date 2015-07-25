package com.d954mas.game.singletons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.d954mas.game.utils.GeneratedFontSkinLoader;

import java.util.HashMap;
import java.util.Map;

public class Assets implements AssetErrorListener {
    private static final Assets instance = new Assets();
    private AssetManager manager;
    private Assets(){
        manager=new AssetManager();
        manager.setErrorListener(this);
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class,
                new FreeTypeFontGeneratorLoader(resolver));
    }

    public static void loadUI(){
        SkinLoader ldr = new GeneratedFontSkinLoader( new InternalFileHandleResolver(), instance.initFonts());
        instance.manager.setLoader(Skin.class, ldr);
        instance.manager.load("ui/uiskin.json", Skin.class);
        instance.manager.finishLoading();

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
        fontsByName.put( "default-font", generator.generateFont( param ));
        generator.dispose();
        return fontsByName;
    }


    public static void loadAll(){

    }

    public static void dispose() {
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
