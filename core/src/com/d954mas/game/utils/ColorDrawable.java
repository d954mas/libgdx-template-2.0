package com.d954mas.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ColorDrawable extends TextureRegionDrawable {
    private Color tintColor;
    private TextureRegion region;
    public ColorDrawable(Color tintColor) {
        this(tintColor, Constants.uiTextures.empty);
    }

    public ColorDrawable(Color tintColor, TextureRegion textureRegion) {
        super();
        this.tintColor = tintColor;
        region = textureRegion;
        this.setRegion(textureRegion);
    }

    public void setTintColor(Color color) {
        this.tintColor = color;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        Color oldColor = batch.getColor();
        batch.setColor(tintColor);
        super.draw(batch, x, y, width, height);
        batch.setColor(oldColor);
    }

    public Color getColor() {
        return tintColor;
    }
}
