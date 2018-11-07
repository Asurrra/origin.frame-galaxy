package com.cyw.origin.frame.galaxy.api;

/**
 * Created by yiwen.chang on 2017/10/13.
 */
public class MyRunnable implements Runnable {

    private int ticketsCount = 100;

    @Override
    public void run() {
        while (ticketsCount > 0) {
            ticketsCount--;
            System.out.println(Thread.currentThread().getName()+"卖了一张票，剩余票数为:"+ticketsCount);
        }
    }
}