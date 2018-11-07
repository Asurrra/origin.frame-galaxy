package com.cyw.origin.frame.galaxy.api;

/**
 * Created by yiwen.chang on 2017/10/13.
 */
public class TicketRunnable {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable mr = new MyRunnable();
        Thread th1 = new Thread(mr,"窗口1");
        Thread th2 = new Thread(mr,"窗口2");
        Thread th3 = new Thread(mr,"窗口3");
        th1.start();
        th2.start();
        th3.start();
    }
}
