package com.luo.game.Base;

public class Config {
    /**
     * 游戏屏幕
     */
    public static int WIDTH = 800, HEIGHT = 600;
    /**
     * 单位
     */
    public static int MIN_X = 22, MIN_Y = 22;
    public static int DISTANCE = 4;

    public static void init(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }
}
