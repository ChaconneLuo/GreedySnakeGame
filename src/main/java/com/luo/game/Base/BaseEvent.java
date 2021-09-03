package com.luo.game.Base;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface BaseEvent {

    default public void init() {
    }


    default public void onKeyPressed(KeyEvent event) {
    }


    default public void onKeyReleased(KeyEvent event) {
    }


    default public void onMouseMoved(MouseEvent event) {
    }
}
