package com.luo.game.View;

import com.luo.game.Base.BaseObject;
import com.luo.game.Base.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverPage extends BaseObject {

    private String str;
    private int myScore;
    private int aiScore;

    public GameOverPage(int myScore, int aiScore) {

        this.myScore = myScore;
        this.aiScore = aiScore;

        if (myScore > aiScore) {
            str = "你赢了";
        } else if (myScore < aiScore) {
            str = "你输了";
        } else {
            str = "平局";
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.web("#333", 0.6));
        gc.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);

        gc.setFill(Color.web("#ddd"));
        gc.fillRoundRect(250, 180, 350, 240, 20,10);

        gc.setFont(Font.font(null, FontWeight.BLACK, 40));
        gc.setFill(Color.web("#333"));
        gc.fillText(str, 350, 230);

        gc.setFont(Font.font(null, FontWeight.BLACK, 20));
        gc.fillText("你的得分：" + myScore, 350, 280);
        gc.fillText("敌方得分：" + aiScore, 350, 320);
    }

    @Override
    public void update() {

    }
}
