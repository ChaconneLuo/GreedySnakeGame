package com.luo.game.Base;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Show extends Application {
    private Group group;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        before();
        group = new Group();
        scene = new Scene(group, Config.WIDTH, Config.HEIGHT);
        after();
        showStage(stage);
    }

    protected void before() {

    }

    protected void after() {

    }

    protected void showStage(Stage stage) {
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void setWindowSize(int Width, int Height) {
        Config.init(Width, Height);
    }

    protected Scene getScene() {
        return scene;
    }

    public Group getGroup() {
        return group;
    }
}
