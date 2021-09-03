package com.luo.game.Component;

import javafx.scene.paint.Color;

public class AutoSnake extends Snake {

    private int cnt = 0;
    private int Max;

    public AutoSnake(int x, int y, Color color, int Max) {
        setX(0);
        setY(0);
        this.color = color;
        this.Max = Max;
    }

    @Override
    public void init() {

        direction = DIRECTIONS.DIR_RIGHT;
        speed = 1;
        length = DEFAULT_LENGTH;
    }

    public void updateDir(Food food) {
        if (cnt > Max) {
            cnt = 0;

            if (food.getX() > getX() && food.getY() > getY()) {
                direction = DIRECTIONS.DIR_RIGHTDOWN;
            } else if (food.getX() > getX() && food.getY() < getY()) {
                direction = DIRECTIONS.DIR_RIGHTUP;
            } else if (food.getX() < getX() && food.getY() < getY()) {
                direction = DIRECTIONS.DIR_LEFTUP;
            } else if (food.getX() < getX() && food.getY() > getY()) {
                direction = DIRECTIONS.DIR_LEFTDOWN;
            }
        } else {
            cnt++;
        }
    }
}
