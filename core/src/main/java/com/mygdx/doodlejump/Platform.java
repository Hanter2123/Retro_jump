package com.mygdx.doodlejump;


import static com.mygdx.doodlejump.DoodleJumpGame.SCREEN_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Platform {
    Coin coin;

    boolean isVist;
    public final static int WIDTH = 80;
    public final static int HEIGHT = 20;

    private Vector2 position;
    private Texture image;

    Random random;

    public Platform(float x, float y){
        image = new Texture("platform.png");
        position = new Vector2(x, y);
        random = new Random();
        isVist = false;

        coin = new Coin(x+WIDTH, y+HEIGHT);
        if (!random.nextBoolean()) {
            coin.collect();
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(image, position.x, position.y, WIDTH, HEIGHT);
        coin.draw(batch);
    }

    public int  playerOnPlatform(Player player){
        if (player.vy <= 0 && player.getRectangle().overlaps(getRectangle())) {
           if(coin.isVisible())  {
               coin.collect();
               return 20;
           }return  10;

        }
        return 0;
    }

    public Rectangle getRectangle(){
        return new Rectangle(position.x, position.y, WIDTH, HEIGHT);
    }

    public void moveUp(float shift) {
        position.y += shift;
    }

    public void moveDown(float shift) {
        position.y -= shift;
    }

    public void update(float delta, Player doodle) {
        coin.setX(position.x+WIDTH/2);
        coin.setY(position.y+HEIGHT);

        if (position.y < -3 * HEIGHT){
            position.y = 830;
            isVist = false;
            position.x = random.nextFloat() * (SCREEN_WIDTH - Platform.WIDTH);


            if (!random.nextBoolean()) {
                coin.collect();
            }
        }
    }
}
