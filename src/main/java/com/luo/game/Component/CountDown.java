package com.luo.game.Component;

import java.util.concurrent.locks.LockSupport;

public class CountDown implements Runnable {

    private int time;
    public Thread thread;
    public volatile boolean canSub = true;

    public CountDown(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        try {
            while (time > 0) {
                if (!canSub) {
                    LockSupport.park();
                } else {
                    time--;
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public int getTime() {
        return time;
    }
}
