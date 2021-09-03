package com.luo.game.View;

import com.luo.game.Base.BaseScreen;
import com.luo.game.Base.Config;
import com.luo.game.Component.*;
import com.luo.game.Controller.CountController;
import com.luo.game.Controller.Impl.CountControllerImpl;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GamePage extends BaseScreen {

    Info info = new Info();

    MySnake userSnake = new MySnake();
    Body userBody = new Body(userSnake);

    AutoSnake aiSnake = new AutoSnake(0, 0, Color.web("#3ceadc", 1.0), 100);
    Body aiBody = new Body(aiSnake);
    AutoSnake aiSnake2 = new AutoSnake(Config.WIDTH, Config.HEIGHT, Color.web("#fd6767", 1.0), 80);
    Body aiBody2 = new Body(aiSnake2);
    AutoSnake aiSnake3 = new AutoSnake(0, Config.HEIGHT, Color.web("#fff", 1.0), 40);
    Body aiBody3 = new Body(aiSnake3);
    AutoSnake aiSnake4 = new AutoSnake(Config.WIDTH, 0, Color.web("#9d9d9d", 1.0), 10);
    Body aiBody4 = new Body(aiSnake4);

    Food food = new Food();


    public GamePage(double width, double height,int maxScore) {

        super(width, height);

        addObject(userBody);
        addObject(userSnake);

        addObject(aiBody);
        addObject(aiSnake);
        addObject(aiBody2);
        addObject(aiSnake2);
        addObject(aiBody3);
        addObject(aiSnake3);
        addObject(aiBody4);
        addObject(aiSnake4);

        addObject(food);

        info.setHistory_score(maxScore);
        addObject(info);

        NowGameState = GameState.GAME_PAUSE;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // 暂停
        if (NowGameState == GameState.GAME_PAUSE) {
            return;
        }

        if (info.getTimes() == 0) {
            NowGameState = GameState.GAME_PAUSE;
            CountController countController = new CountControllerImpl();
            countController.setMaxCount(getName(),userSnake.score());
            GameOverPage gameOverPage = new GameOverPage(userSnake.score(), aiSnake.score() + aiSnake2.score() + aiSnake3.score() + aiSnake4.score());
            addObject(gameOverPage);
        }

        super.draw(gc);
    }

    @Override
    public void update() {
        // 暂停
        if (NowGameState == GameState.GAME_PAUSE) {
            return;
        }

        //设置显示生命和积分
        info.setLength(userSnake.getLength());
        info.setKills(userSnake.getKills());
        info.setScore(userSnake.score());
        info.setAiScore(aiSnake.score() + aiSnake2.score() + aiSnake3.score() + aiSnake4.score());
        //调用更新操作
        super.update();

        crossBorder(userSnake);
        crossBorder(aiSnake);
        crossBorder(aiSnake2);
        crossBorder(aiSnake3);
        crossBorder(aiSnake4);

        // 吃到
        eatFood(userSnake);
        eatFood(aiSnake);
        eatFood(aiSnake2);
        eatFood(aiSnake3);
        eatFood(aiSnake4);

        collisionTwoWay(userSnake, userBody, aiSnake, aiBody);
        collisionTwoWay(userSnake, userBody, aiSnake2, aiBody2);
        collisionTwoWay(userSnake, userBody, aiSnake3, aiBody3);
        collisionTwoWay(userSnake, userBody, aiSnake4, aiBody4);

        aiSnake.updateDir(food);
        aiSnake2.updateDir(food);
        aiSnake3.updateDir(food);
        aiSnake4.updateDir(food);

    }

    public void crossBorder(Snake baseSnake) {

        if (baseSnake.getX() < 0) {
            baseSnake.setX(Config.WIDTH);
        }
        if (baseSnake.getX() > Config.WIDTH) {
            baseSnake.setX(0);
        }
        if (baseSnake.getY() < 0) {
            baseSnake.setY(Config.HEIGHT);
        }
        if (baseSnake.getY() > Config.HEIGHT) {
            baseSnake.setY(0);
        }
    }

    public void eatFood(Snake baseSnake) {

        if (baseSnake.isCollisionWith(food)) {
            baseSnake.addScore(3);
            baseSnake.addLength();
            food.setVisible(false);
        }
    }

    /**
     *
     * 互相杀死
     * @param passive 被杀
     * @param passiveBody
     * @param active
     * @param activeBody
     */

    public void collisionTwoWay(Snake passive, Body passiveBody, Snake active, Body activeBody) {

        collision(passive, passiveBody, active, activeBody);
        collision(active, activeBody, passive, passiveBody);
    }

    public void collision(Snake passive, Body passiveBody, Snake active, Body activeBody) {

        if (passiveBody.isCollisionWith(active)) {
            active.death();
            activeBody.setVisible(false);
            passive.addKills();
            passive.addScore(3);
            return;
        }
    }

    @Override
    public void onKeyReleased(KeyEvent event) {
        // 暂停
        if (event.getCode() == KeyCode.ESCAPE) {
            if (NowGameState == GameState.GAME_PAUSE) {
                NowGameState = GameState.GAME_START;
                info.resume();
            } else {
                NowGameState = GameState.GAME_PAUSE;
                info.sleep();
            }
        }

    }

    public void setGameState(int i) {
        if (i == 1) {
            NowGameState = GameState.GAME_START;
        }
    }
}