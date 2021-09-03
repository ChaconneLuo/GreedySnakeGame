package com.luo.game.Component;

import com.luo.game.Base.BaseObject;
import com.luo.game.Base.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.locks.LockSupport;

public class Info extends BaseObject {
    private boolean isLive;
    private int score = 0;
    private int aiScore = 0;
    private int length = 0;
    private int kills = 0;
    private int hp = 0;
    private int history_score = 0;
    private CountDown countDown = new CountDown(Integer.parseInt((String) ResourceBundle.getBundle("config").getObject("default_time"))); //Test

    public Info() {
        init();
        countDown.start();
    }

    public void init() {
        super.init();
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(Color.web("#666"));
        gc.setFont(Font.font(null, FontWeight.BLACK, 20));
        gc.fillText("长度:" + getLength(), 16, 34);
        gc.fillText("击杀:" + getKills(), 16, 62);
        gc.fillText("历史最高:" + getHistory_score(), 16, 90);

        gc.setFill(Color.web("#999", 0.5));
        gc.fillRoundRect(Config.WIDTH - 160, 5, 150, 70, 15, 15);
        gc.fillRoundRect(Config.WIDTH / 2 - 80, Config.HEIGHT - 18, 150, 20, 10, 10);

        gc.setFill(Color.web("#666"));
        gc.fillText("我的分数：" + getScore(), Config.WIDTH - 150, 30);
        gc.fillText("敌方分数：" + getAIScore(), Config.WIDTH - 150, 60);

        gc.setFont(Font.font(null, FontWeight.BLACK, 12));
        gc.fillText(" ESC键暂停", Config.WIDTH / 2 - 40, Config.HEIGHT - 4);

        gc.setFont(Font.font(null, FontWeight.BLACK, 40));
        gc.fillText(String.format("%02d", (countDown.getTime() / 60)) + ":" + String.format("%02d", (countDown.getTime() % 60)), Config.WIDTH / 2 - 50, 50);
    }

    @Override
    public void update() {

    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAIScore() {
        return aiScore;
    }

    public void setAiScore(int aiScore) {
        this.aiScore = aiScore;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public CountDown getCountDown() {
        return countDown;
    }

    public void setCountDown(CountDown countDown) {
        this.countDown = countDown;
    }

    public int getTimes() {

        return countDown.getTime();
    }

    public int getHistory_score() {
        return history_score;
    }

    public void setHistory_score(int history_score) {
        this.history_score = history_score;
    }

    public void sleep() {
        countDown.canSub = false;
    }

    public void resume() {
        countDown.canSub = true;
        LockSupport.unpark(countDown.thread);
    }
}
