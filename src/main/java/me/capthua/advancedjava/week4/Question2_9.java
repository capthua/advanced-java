package me.capthua.advancedjava.week4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

//CountDownLatch
public class Question2_9 extends AbstractQuestion2{

    private static Integer result;


    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread calculateTask = new Thread(new CalculationTask(cyclicBarrier));
        calculateTask.start();
        cyclicBarrier.await();
        long start=System.currentTimeMillis();
        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class CalculationTask implements Runnable{
        private CyclicBarrier cyclicBarrier;
        CalculationTask(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier=cyclicBarrier;
        }
        @Override
        public void run() {
            result=sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
