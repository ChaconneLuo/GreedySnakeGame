package com.luo.game.Component;

import com.luo.game.Base.BaseObject;
import com.luo.game.Base.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class Snake extends BaseObject {

    protected enum DIRECTIONS {
        DIR_UP, DIR_DOWN, DIR_LEFT, DIR_RIGHT, DIR_LEFTUP, DIR_LEFTDOWN, DIR_RIGHTUP, DIR_RIGHTDOWN
    };
    public static final int DEFAULT_LENGTH = 12;
    AtomicInteger score = new AtomicInteger(0);
    Color color;
    double speed;
    int length;
    int kills;
    DIRECTIONS direction;

    public Snake() {
        super.init();
        score.set(0);
        kills = 0;
        setWidth(Config.MIN_X);
        setHeight(Config.MIN_Y);
        init();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void update() {
        if (direction == DIRECTIONS.DIR_UP) {
            moveY(-speed);
        } else if (direction == DIRECTIONS.DIR_DOWN) {
            moveY(speed);
        } else if (direction == DIRECTIONS.DIR_LEFT) {
            moveX(-speed);
        } else if (direction == DIRECTIONS.DIR_RIGHT) {
            moveX(speed);
        } else if (direction == DIRECTIONS.DIR_LEFTUP) {
            moveX(-speed * Math.sqrt(2) / 2);
            moveY(-speed * Math.sqrt(2) / 2);
        } else if (direction == DIRECTIONS.DIR_LEFTDOWN) {
            moveX(-speed * Math.sqrt(2) / 2);
            moveY(speed * Math.sqrt(2) / 2);
        } else if (direction == DIRECTIONS.DIR_RIGHTUP) {
            moveX(speed * Math.sqrt(2) / 2);
            moveY(-speed * Math.sqrt(2) / 2);
        } else if (direction == DIRECTIONS.DIR_RIGHTDOWN) {
            moveX(speed * Math.sqrt(2) / 2);
            moveY(speed * Math.sqrt(2) / 2);
        }
    }

    public void death() {
        setX(0);
        setY(0);
        direction = DIRECTIONS.DIR_RIGHT;
        this.length = DEFAULT_LENGTH;
    }

    public int score() {

        return score.get();
    }

    public void addScore(int delta) {

        score.incrementAndGet();  //自增加一分
        score.addAndGet(delta);    //再加对应的击杀分
    }

    public int getKills() {

        return kills;
    }

    public void addKills() {

        kills += 1;
    }

    public int getLength() {

        return length;
    }

    public void addLength() {

        this.length += 1;
    }

    public Color getSnakeColor() {

        return color;
    }
}
