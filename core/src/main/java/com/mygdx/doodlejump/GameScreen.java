package com.mygdx.doodlejump;

import static com.mygdx.doodlejump.DoodleJumpGame.SCREEN_HEIGHT;
import static com.mygdx.doodlejump.DoodleJumpGame.SCREEN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Sort;

import java.util.Random;

public class GameScreen implements Screen {

    DoodleJumpGame game;
    private Platform platform;
    private Player doodle;
    Texture backGrund;
    TextRender text;
    int score;
    float timer;
    boolean isdown;

    Platform[] platforms;
    Random random;

    private Button buttonPause;

    private static final float PLAYER_THRESHOLD = DoodleJumpGame.SCREEN_HEIGHT / 3f;

    public GameScreen(DoodleJumpGame game) {
        this.game = game;
        text = new TextRender(40, new Color(Color.valueOf("9932CC")));

//        platform = new Platform(50, 60);
        int N = 10;
        platforms = new Platform[N];
        random = new Random();

        for (int i = 0; i < N; i++) {
            platforms[i] = new Platform(random.nextFloat() * (SCREEN_WIDTH - Platform.WIDTH), i * 90 + 20);
        }

        platform = platforms[0];
        doodle = new Player(platform.getRectangle().x, platform.getRectangle().y + Platform.HEIGHT + 4);

        buttonPause = new Button(SCREEN_WIDTH - 110, SCREEN_HEIGHT - 70, 100, 60, "pause.jpg");
        backGrund = new Texture("backgraund.png");
        score = 0;
        timer = -1;

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f); // заливка экрана


        if (Gdx.input.isTouched()) {
            float inputX = Gdx.input.getX();
            doodle.jump(inputX > (float) SCREEN_WIDTH / 2, delta);
        } else {
            doodle.stop();
        }
        if ((score + 1) % 5 == 0) {
            doodle.nextskin();


        }

        doodle.update(delta);

        // ОТРИСОВКА
        game.batch.begin();
        game.batch.draw(backGrund, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        doodle.draw(game.batch);

        for (Platform platform1 : platforms) {
            platform1.draw(game.batch);
            platform1.update(delta, doodle);
            int newSCORE = platform1.playerOnPlatform(doodle);
            if (newSCORE > 0) {
                if (!platform1.isVist) {
                    score += newSCORE;
                    platform1.isVist = true;
                }
                System.out.println("jump");
                doodle.jump();
            }
        }

        buttonPause.draw(game.batch);
        text.drawText(game.batch, "score " + score, SCREEN_WIDTH / 2 - 40, SCREEN_HEIGHT - 20);
        game.batch.end();
        System.out.println(score);

        if (Gdx.input.isTouched()) {
            if (buttonPause.isClick(Gdx.input.getX(), SCREEN_HEIGHT - Gdx.input.getY())) {
                System.out.println("click");
            }
        }


        if (doodle.getY() > PLAYER_THRESHOLD) {
            float shift = doodle.getY() - PLAYER_THRESHOLD;
            for (Platform platform1 : platforms) {
                platform1.moveDown(shift);
            }
            doodle.moveUp(-shift);
        }

        if (doodle.getY() < 0) {
            float shift = Math.abs(doodle.getY());
            for (Platform platform1 : platforms) {
                platform1.moveDown(-shift);

            }
            doodle.moveUp(shift);
            if (!isdown) {
                timer = 0.5f;
            }
            isdown = true;

        }
        if (isdown) {
            timer -= delta;
            if (timer < 0) {
                game.setHashScore(score);
                game.setScreen(game.menuScreen);
        }
    }

}

@Override
public void show() {

}

@Override
public void resize(int width, int height) {

}

@Override
public void pause() {

}

@Override
public void resume() {

}

@Override
public void hide() {

}

@Override
public void dispose() {

}
}
