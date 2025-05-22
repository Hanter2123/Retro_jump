package com.mygdx.doodlejump;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Coin {

    public Texture texture;

        private float x;                          // Позиция по оси X
        private float y;                          // Позиция по оси Y
        private boolean visible;                  // Статус видимости монеты

        public Coin(float x, float y) {
            this.x = x;
            this.y = y;
            this.visible = true;
            texture = new Texture("coin_fresh.png");

        }

        public void draw(SpriteBatch batch) {
            if (visible) {
                // Предположим, что у нас есть текстура монеты
                batch.draw(texture, x, y, 30, 30); // Размер текстуры монеты 20x20 пикселей
            }
        }

        public boolean collidesWith(Rectangle playerRect) {
            Rectangle coinRect = new Rectangle(x, y, 30, 30);
            return coinRect.overlaps(playerRect);
        }

        public void collect() {
            visible = false;
        }

        public boolean isVisible() {
            return visible;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

    public void setX(float x) {
            this.x  = x;
    }

    public void setY(float y) {
            this.y = y;
    }
}


