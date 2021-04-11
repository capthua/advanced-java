package me.capthua.advancedjava.week4;

import java.util.concurrent.*;

//使用Futrue
public class Question2_2 extends AbstractQuestion2{
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result=executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        long start=System.currentTimeMillis();
        try {
            System.out.println("异步计算结果为："+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        executor.shutdown();
        // 然后退出main线程
    }
}
