package com.mygdx.doodlejump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextRender {
    private BitmapFont font;
    private SpriteBatch batch;

    public static BitmapFont generate(int size, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("RuneScape-ENA.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = size;
        parameter.color = color;

        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;

    }

    public TextRender(int size, Color color) {
        font = generate(size, color);

    }

    public void drawText(SpriteBatch batch, String Text, float x, float y) {
        font.draw(batch, Text, x, y);
    }

    public BitmapFont getFont() {
        return font;
    }
}
