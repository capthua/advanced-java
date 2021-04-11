package me.capthua.advancedjava.week4;

import java.util.concurrent.TimeUnit;

//等待固定时间
public class Question2_4 extends AbstractQuestion2{

    private static Integer result;

    public static void main(String[] args) {
        // 在这里创建一个线程或线程池，
        Runnable task= new Runnable() {
            @Override
            public void run() {
                result=sum();
                System.out.println("子线程中result:"+result);
            }
        };
        Thread calculateTask = new Thread(task);
        System.out.println("异步计算结果1为："+result);
        long start=System.currentTimeMillis();
        calculateTask.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步计算结果2为："+result);
    }
}
