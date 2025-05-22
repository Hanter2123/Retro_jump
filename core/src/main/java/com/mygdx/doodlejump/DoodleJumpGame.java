package com.mygdx.doodlejump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class DoodleJumpGame extends Game {
    public SpriteBatch batch;

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 800;
    private int hashScore;
    public GameScreen gameScreen;
    public MenuScreen menuScreen;


    @Override
    public void create() {
        batch = new SpriteBatch();
        hashScore = 0;

        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);

        this.setScreen(menuScreen);
    }

    public void setHashScore(int score) {
        hashScore = score;
    }

    public int getHashScore() {
        return hashScore;
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}
