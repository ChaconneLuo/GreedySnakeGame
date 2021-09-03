package com.luo.game.Component;

import com.luo.game.Base.BaseObject;
import com.luo.game.Base.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light.Point;

import java.util.LinkedList;

public class Body extends BaseObject {

    Snake snake;
    int length;
    LinkedList<Point> list = new LinkedList<Point>();
    private int bias = 4;

    public Body(Snake snake) {
        this.snake = snake;

        this.xProperty().bindBidirectional(snake.xProperty());
        this.yProperty().bindBidirectional(snake.yProperty());
        this.widthProperty().bindBidirectional(snake.widthProperty());
        this.heightProperty().bindBidirectional(snake.heightProperty());

        init();
    }

    public void init()
    {
        super.init();
        this.length = snake.length;
        list.clear();
        for (int i = 0; i < snake.length; i++) {
            Point point = new Point();
            point.setX(getX() - Config.DISTANCE * i);
            point.setY(getY());
            list.add(point);
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        if(snake.length<=1)
        {
            return;
        }
        Point first = list.getFirst();
        if(first.getX() + Config.DISTANCE <= getX() || first.getX() - Config.DISTANCE >= getX() || first.getY() + Config.DISTANCE <= getY() || first.getY() - Config.DISTANCE >= getY())
        {
            Point point = new Point();
            point.setX(getX());
            point.setY(getY());
            // 添加第一个头
            list.addFirst(point);
            // 如果吃到了就不移除了，没吃到就删除最后一个
            if (this.length < snake.length) {
                this.length = this.length + 1;
            } else {
                list.removeLast();
            }
        }
        gc.setFill(snake.getSnakeColor());
        for (Point point : list) {
            gc.fillOval(point.getX(), point.getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void update() {
        if (!isVisible()) {
            init();
            setVisible(true);
            return;
        }
    }
    public boolean isCollisionWith(BaseObject baseObject) {

        for (Point point : list) {
            if (isCollisionWith(point.getX(), point.getY(), baseObject))
                return true;
        }
        return false;
    }

    private boolean isCollisionWith(double x, double y, BaseObject baseObject) {

        if (x + getWidth() - bias > baseObject.getX() && x < baseObject.getX() + baseObject.getWidth() - bias
                && y + getHeight() - bias > baseObject.getY() && y < baseObject.getY() + baseObject.getHeight() - bias) {
            return true;
        }
        return false;
    }
}
