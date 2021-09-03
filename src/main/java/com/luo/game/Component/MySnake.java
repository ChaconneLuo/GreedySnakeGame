package com.luo.game.Component;

import com.luo.game.Base.Config;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class MySnake extends Snake {
    public void init() {
        direction = DIRECTIONS.DIR_RIGHT;
        color = Color.web("#38a3ff", 1.0);
        speed = 2;
        length = DEFAULT_LENGTH;
        setX(Config.WIDTH / 2);
        setY(Config.HEIGHT / 2); //屏幕中心
    }

    @Override
    public void onKeyPressed(KeyEvent event) {

        KeyCode tmpCode = event.getCode();
        if ((tmpCode == KeyCode.UP && direction == DIRECTIONS.DIR_DOWN) //反向不处理
                || (tmpCode == KeyCode.DOWN && direction == DIRECTIONS.DIR_UP)
                || (tmpCode == KeyCode.RIGHT && direction == DIRECTIONS.DIR_LEFT)
                || (tmpCode == KeyCode.LEFT && direction == DIRECTIONS.DIR_RIGHT)
        ) {
            return;
        }

        updateDir(tmpCode, direction);
    }

    public void updateDir(KeyCode keyCode, DIRECTIONS dir) {

        if (keyCode == KeyCode.UP) {
            direction = switch (dir) {
                case DIR_LEFTDOWN -> DIRECTIONS.DIR_LEFT;
                case DIR_LEFT -> DIRECTIONS.DIR_LEFTUP;
                case DIR_LEFTUP, DIR_RIGHTUP -> DIRECTIONS.DIR_UP;
                case DIR_RIGHTDOWN -> DIRECTIONS.DIR_RIGHT;
                case DIR_RIGHT -> DIRECTIONS.DIR_RIGHTUP;
                default -> direction;
            };
        } else if (keyCode == KeyCode.DOWN) {
            direction = switch (dir) {
                case DIR_LEFTDOWN, DIR_RIGHTDOWN -> DIRECTIONS.DIR_DOWN;
                case DIR_LEFT -> DIRECTIONS.DIR_LEFTDOWN;
                case DIR_LEFTUP -> DIRECTIONS.DIR_LEFT;
                case DIR_RIGHT -> DIRECTIONS.DIR_RIGHTDOWN;
                case DIR_RIGHTUP -> DIRECTIONS.DIR_RIGHT;
                default -> direction;
            };
        } else if (keyCode == KeyCode.LEFT) {
            direction = switch (dir) {
                case DIR_LEFTUP, DIR_LEFTDOWN -> DIRECTIONS.DIR_LEFT;
                case DIR_UP -> DIRECTIONS.DIR_LEFTUP;
                case DIR_DOWN -> DIRECTIONS.DIR_LEFTDOWN;
                case DIR_RIGHTUP -> DIRECTIONS.DIR_UP;
                case DIR_RIGHTDOWN -> DIRECTIONS.DIR_DOWN;
                default -> direction;
            };
        } else if (keyCode == KeyCode.RIGHT) {
            direction = switch (dir) {
                case DIR_LEFTUP -> DIRECTIONS.DIR_UP;
                case DIR_LEFTDOWN -> DIRECTIONS.DIR_DOWN;
                case DIR_UP -> DIRECTIONS.DIR_RIGHTUP;
                case DIR_DOWN -> DIRECTIONS.DIR_RIGHTDOWN;
                case DIR_RIGHTUP, DIR_RIGHTDOWN -> DIRECTIONS.DIR_RIGHT;
                default -> direction;
            };
        }
    }
}
