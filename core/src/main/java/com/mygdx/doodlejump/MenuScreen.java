package com.mygdx.doodlejump;

import static com.mygdx.doodlejump.DoodleJumpGame.SCREEN_HEIGHT;
import static com.mygdx.doodlejump.DoodleJumpGame.SCREEN_WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {
    DoodleJumpGame game;
    //    OrthographicCamera camera;
//    Viewport viewport;
//    BitmapFont font;
    TextRender font;

    // Кнопки меню
    Texture backGrund ;
    Button startGameBtn;
    Button optionsBtn;
    Button exitBtn;
    private Button[] buttons;


    public MenuScreen(DoodleJumpGame doodleJumpGame) {
        // Инициализация камеры и спрайтового батча
//        camera = new OrthographicCamera();
//        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
//        batch = new SpriteBatch();
        backGrund = new Texture("backgraund.png");
        game = doodleJumpGame;

        // Шрифт текста
        font = new TextRender(40, new Color(Color.valueOf("9932CC")));

        // Создаем кнопки
        createButtons();
        buttons = new Button[]{startGameBtn, optionsBtn, exitBtn};

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0f, 0.8f, 1); // Черный фон
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        camera.update(); // Обновляем камеру перед рендерингом
//        batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backGrund, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (Button btn : getButtons()) { // Отображаем каждую кнопку
            btn.draw(game.batch);
        }
        font.drawText(game.batch,"score " + game.getHashScore(), SCREEN_WIDTH / 2 - 40, SCREEN_HEIGHT - 20);

        // Если кнопка нажата, выполняем соответствующее действие
        handleInput();
        game.batch.end();
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

    private void createButtons() {
        final int buttonWidth = 200;
        final int buttonHeight = 50;

        // Положение кнопок относительно центра экрана
        float screenCenterX = Gdx.graphics.getWidth() / 2;
        float topY = Gdx.graphics.getHeight() * 0.7f;
        float verticalGap = 80;

        // Начальная позиция первой кнопки сверху
        float currentY = topY;

        // Создание кнопок с различными изображениями и действиями
        startGameBtn = new Button(screenCenterX - buttonWidth / 2, currentY,
            buttonWidth, buttonHeight, "Start1.png");
        currentY -= verticalGap;

        optionsBtn = new Button(screenCenterX - buttonWidth / 2, currentY,
            buttonWidth, buttonHeight, "Settings1.png");
        currentY -= verticalGap;

        exitBtn = new Button(screenCenterX - buttonWidth / 2, currentY,
            buttonWidth, buttonHeight, "Back1.png");
    }

    // Метод теперь возвращает сам массив
    private Button[] getButtons() {
        return buttons;
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();

            // Преобразуем координаты касания относительно камеры
            touchY = Gdx.graphics.getHeight() - touchY;

            for (Button btn : getButtons()) {
                if (btn.isClick(touchX, touchY)) {
                    if (btn.equals(startGameBtn)) {
                        System.out.println("Игра начата!");
                        game.setScreen(game.gameScreen);
                    } else if (btn.equals(optionsBtn)) {
                        System.out.println("Открываются настройки игры.");
                    } else if (btn.equals(exitBtn)) {
                        Gdx.app.exit();
                    }
                }
            }
        }
    }
}
