package com.d954mas.game.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class packAtlases {
    public static void main(String[] args){
        TexturePacker.Settings settings=new TexturePacker.Settings();
        settings.fast=true;
        settings.duplicatePadding=true;
        settings.maxHeight=2048;
        settings.maxWidth=2048;
        TexturePacker.process(settings, "./game", "../android/assets/data", "game");
        TexturePacker.process(settings, "./ui", "../android/assets/ui", "uiskin");
    }
}
