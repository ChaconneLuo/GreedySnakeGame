package com.luo.game.Base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

public abstract class BaseObject implements BaseEvent{
    protected DoubleProperty Width = new SimpleDoubleProperty(0);
    protected DoubleProperty Height = new SimpleDoubleProperty(0);
    protected DoubleProperty X = new SimpleDoubleProperty(0);
    protected DoubleProperty Y = new SimpleDoubleProperty(0);
    protected BooleanProperty Update = new SimpleBooleanProperty(true);
    protected BooleanProperty Visible = new SimpleBooleanProperty(true);

    private int bias = 4;

    public BaseObject(double x, double y, double width, double height) {
        this.X = new SimpleDoubleProperty(x);
        this.Y = new SimpleDoubleProperty(y);
        this.Width = new SimpleDoubleProperty(width);
        this.Height = new SimpleDoubleProperty(height);
    }

    public BaseObject() {
        this.X = new SimpleDoubleProperty(0);
        this.Y = new SimpleDoubleProperty(0);
        this.Width = new SimpleDoubleProperty(0);
        this.Height = new SimpleDoubleProperty(0);
    }

    public void init() {
        setVisible(true);
    }

    public abstract void draw(GraphicsContext gc);

    public abstract void update();

    public double getWidth() {
        return Width.get();
    }

    public DoubleProperty widthProperty() {
        return Width;
    }

    public void setWidth(double width) {
        this.Width.set(width);
    }

    public double getHeight() {
        return Height.get();
    }

    public DoubleProperty heightProperty() {
        return Height;
    }

    public void setHeight(double height) {
        this.Height.set(height);
    }

    public double getX() {
        return X.get();
    }

    public DoubleProperty xProperty() {
        return X;
    }

    public void setX(double x) {
        this.X.set(x);
    }

    public double getY() {
        return Y.get();
    }

    public DoubleProperty yProperty() {
        return Y;
    }

    public void setY(double y) {
        this.Y.set(y);
    }

    public boolean isUpdate() {
        return Update.get();
    }

    public BooleanProperty updateProperty() {
        return Update;
    }

    public void setUpdate(boolean update) {
        this.Update.set(update);
    }

    public boolean isVisible() {
        return Visible.get();
    }

    public BooleanProperty visibleProperty() {
        return Visible;
    }

    public void setVisible(boolean visible) {
        this.Visible.set(visible);
    }

    public void moveX(double x) {
        this.X.set(getX() + x);
    }

    public void moveY(double y) {
        this.Y.set(getY() + y);
    }

    public boolean isCollisionWith(BaseObject baseObject) {
        if (getX() + getWidth() - bias > baseObject.getX() && getX() < baseObject.getX() + baseObject.getWidth() - bias
                && getY() + getHeight() - bias > baseObject.getY() && getY() < baseObject.getY() + baseObject.getHeight() - bias) {
            return true;
        }
        return false;
    }
}
