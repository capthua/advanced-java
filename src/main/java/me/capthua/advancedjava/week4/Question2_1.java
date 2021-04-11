package me.capthua.advancedjava.week4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

//使用futureTask
public class Question2_1 extends AbstractQuestion2{
    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        FutureTask<Integer> task= new FutureTask<>(() ->
                sum()
        );
        new Thread(task).start();
        // 确保  拿到result 并输出
        try {
            System.out.println("异步计算结果为："+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
