package me.capthua.advancedjava.week4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//CountDownLatch
public class Question2_8 extends AbstractQuestion2{

    private static Integer result;


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread calculateTask = new Thread(new CalculationTask(countDownLatch));
        long start=System.currentTimeMillis();
        calculateTask.start();
        countDownLatch.await();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class CalculationTask implements Runnable{
        private CountDownLatch latch;
        CalculationTask(CountDownLatch latch) {
            this.latch=latch;
        }
        @Override
        public void run() {
            result=sum();
            latch.countDown();
        }
    }
}
