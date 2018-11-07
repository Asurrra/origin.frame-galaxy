package com.cyw.origin.frame.galaxy.api;

/**
 * Created by yiwen.chang on 2017/10/13.
 */
class MyThread extends Thread{

    private int ticketsCount = 5;
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (ticketsCount > 0) {
            ticketsCount--;
            System.out.println(name+"卖了一张票，剩余票数为:"+ticketsCount);
        }
    }
}