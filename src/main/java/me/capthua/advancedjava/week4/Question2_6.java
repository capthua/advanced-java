package me.capthua.advancedjava.week4;

import java.util.concurrent.TimeUnit;

//wait notify
public class Question2_6 extends AbstractQuestion2{

    private static Integer result;
    private static final Object lock=new Object();

    public static void main(String[] args) {
        // 在这里创建一个线程或线程池，
        Runnable task= new Runnable() {
            @Override
            public void run() {
                result=sum();
                System.out.println("子线程中result:"+result);
                synchronized (lock){
                    lock.notify();
                }
            }
        };
        Thread calculateTask = new Thread(task);
        long start=System.currentTimeMillis();
        calculateTask.start();
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}
