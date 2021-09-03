package com.luo.game.Base;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseScreen extends Canvas implements BaseEvent{
    protected enum GameState {
        GAME_MENU, GAME_START, GAME_CONTINUE, GAME_HELP, GAME_SET, GAME_END, GAME_PAUSE
    };

    private List<BaseObject> Objects = new ArrayList<BaseObject>();
    private Timeline timeline;
    private KeyFrame keyFrame;
    private int duration = 10;
    protected String Name;
    protected GameState NowGameState = GameState.GAME_MENU;

    public BaseScreen(double width, double height) {
        super(width, height);
        initTimeLine();
    }

    public void initEvents() {
        getParent().getScene().setOnKeyPressed(event -> {
            onKeyPressed(event);
        });

        getParent().getScene().setOnKeyReleased(event -> {
            onKeyReleased(event);
        });

        getParent().getScene().setOnMouseMoved(event -> {
            onMouseMoved(event);
        });
    }

    public void onKeyPressed(KeyEvent event) {
        for (BaseObject wObject : Objects) {
            wObject.onKeyPressed(event);
        }
    }

    public void onKeyReleased(KeyEvent event) {
        for (BaseObject wObject : Objects) {
            wObject.onKeyReleased(event);
        }
    }

    public void onMouseMoved(MouseEvent event) {
        for (BaseObject wObject : Objects) {
            wObject.onMouseMoved(event);
        }
    }

    public void addObject(BaseObject baseObject) {
        this.Objects.add(baseObject);
    }


    public void removeObject(BaseObject baseObject) {
        this.Objects.remove(baseObject);
    }


    public void removeObjectAtIndex(int index) {
        this.Objects.remove(index);
    }



    public void draw(GraphicsContext gc) {
        gc.setEffect(null);
        gc.clearRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < Objects.size(); i++) {
            BaseObject wObject = Objects.get(i);
            if (wObject.isVisible()) {
                wObject.draw(gc);
            }
        }
    }

    public void update() {
        for (int i = 0; i < Objects.size(); i++) {
            BaseObject wObject = Objects.get(i);
            if (wObject.isUpdate()) {
                Objects.get(i).update();
            }
        }
    }

    private void initTimeLine() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                draw(getGraphicsContext2D());
                update();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
    }
    public void start() {
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void stop() {
        timeline.stop();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
