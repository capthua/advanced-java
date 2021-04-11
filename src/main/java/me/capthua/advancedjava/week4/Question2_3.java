package me.capthua.advancedjava.week4;

import java.util.concurrent.*;

//使用join
public class Question2_3 extends AbstractQuestion2{

    private static Integer result=0;

    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        Runnable task= new Runnable() {
            @Override
            public void run() {
                result=sum();
            }
        };
        Thread calculateTask = new Thread(task);
        calculateTask.start();
        try {
            calculateTask.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
