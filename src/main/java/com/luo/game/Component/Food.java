package com.luo.game.Component;

import com.luo.game.Base.BaseObject;
import com.luo.game.Base.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Food extends BaseObject {

    private Random random = new Random();
    private Color color;

    public Food() {
        init();
    }

    public void init() {

        super.init();
//        StackTraceElement stackTraceElement[] = (new Throwable()).getStackTrace();
//        for (int i = 0; i < stackTraceElement.length; i++) {
//            System.out.println(stackTraceElement[i].getClassName());
//        }
        createRandomFood();
        setWidth(Config.MIN_X);
        setHeight(Config.MIN_Y);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void update() {
        if (!isVisible()) {
            color = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            createRandomFood();
            setVisible(true);
        }
    }

    private void createRandomFood() {

        int x = random.nextInt(Config.WIDTH);
        int y = random.nextInt(Config.HEIGHT);
        setX(x);
        setY(y);
    }
}
