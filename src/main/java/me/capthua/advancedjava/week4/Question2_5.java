package me.capthua.advancedjava.week4;

import java.util.concurrent.TimeUnit;

//使用轮询
public class Question2_5 extends AbstractQuestion2{

//    private volatile static Integer result;
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
        long start=System.currentTimeMillis();
        calculateTask.start();

        for(;;){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(result!=null){
                System.out.println("异步计算结果为："+result);
                System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
                break;
            }
        }
    }
}
