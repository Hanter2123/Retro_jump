package com.mygdx.doodlejump;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;

public class Player {
    private static final float GRAVITY = -20;
    private final static int WIDTH = 70;
    private final static int HEIGHT = 60;

    private float speed;
    private float jumpHeight;

    private int health;

    private float x, y;

    private Texture image;

    public float vx;
    public float vy;
    boolean isJump;
    int numberskin;

    public Player(float x, float y){
        numberskin = 0;
        image = new Texture("walk3.png");
        this.x = x;
        this.y = y;
        speed = 400;
        health = 100;
        jumpHeight = 900;
        vx = 0;
        vy = 0;
        isJump = false;
    }

    public void update(float deltaTime){
        vy += GRAVITY;

        y += vy * deltaTime;
        x += vx * deltaTime;


        if (x <= 0){
            x = DoodleJumpGame.SCREEN_WIDTH - WIDTH;
        }
        if (DoodleJumpGame.SCREEN_WIDTH <= x){
            x = 0;
        }
    }

    public void jump(boolean flag, float delta){
        if (flag) {
            vx += speed * delta;
        } else {
            vx -= speed * delta;
        }
    }

    public void jump(){
        vy = jumpHeight;
    }

    public void draw(SpriteBatch batch){
        batch.draw(image, x, y, WIDTH, HEIGHT);
    }

    public Rectangle getRectangle(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void stop() {
        vx *= 0.9f;
        if (Math.abs(vx) < 5){
            vx = 0;
        }
    }

    public float getY() {
        return y;
    }

    public void moveUp(float v) {
        y += v;
    }

    public void nextskin() {
        switch (numberskin){
            case 0 :
                image = new Texture("walk1.png");
                break;
            case 1 :
                image = new Texture("walk2.png");
                break;
            case 2:
                image = new Texture("walk3.png");
                break;
            case 3:
                image = new Texture("walk4.png");
                break;


        }
        numberskin ++;


    }
}
