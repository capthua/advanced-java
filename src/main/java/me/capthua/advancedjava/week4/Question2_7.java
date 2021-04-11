package me.capthua.advancedjava.week4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//lock condition
public class Question2_7 extends AbstractQuestion2{

    private static Integer result;
    private static final Lock lock =new ReentrantLock(true);
    private static final Condition condition=lock.newCondition();

    public static void main(String[] args) {
        // 在这里创建一个线程或线程池，
        Runnable task= new Runnable() {
            @Override
            public void run() {
                result=sum();
                System.out.println("子线程中result:"+result);
                lock.lock();
                condition.signalAll();
                lock.unlock();
            }
        };
        Thread calculateTask = new Thread(task);
        long start=System.currentTimeMillis();
        calculateTask.start();
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("异步计算结果为："+result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}
