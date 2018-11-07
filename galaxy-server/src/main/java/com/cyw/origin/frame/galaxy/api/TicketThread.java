package com.cyw.origin.frame.galaxy.api;

/**
 * Created by yiwen.chang on 2017/10/13.
 */
public class TicketThread {

    public static void main(String[] args) {

        MyThread mt1 = new MyThread("窗口1");
        MyThread mt2 = new MyThread("窗口2");
        MyThread mt3 = new MyThread("窗口3");

        mt1.start();
        mt2.start();
        mt3.start();
    }
}
