package com.mygdx.doodlejump;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


public class Button {
    private Vector2 position;
    private Texture texture;

    private int width;
    private int height;

    public Button(float x, float y, int width, int height, String path){
        position = new Vector2(x, y);
        this.height = height;
        this.width = width;

        texture = new Texture(path);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, position.x, position.y, width, height);
    }

    public boolean isClick(float x, float y){
        return (position.x < x && position.x + width > x) && (position.y < y && position.y + height > y);
    }

}
