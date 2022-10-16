package main.java.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TypesOfThreadPool {
    public static void main(String[] args) {

        //1st type(by newFixedThreadPool)

        //create a pool
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //submit task for execution
        for (int i=0;i<15;i++)
            executorService.execute(new Task());

        executorService.shutdown();

        //2nd type(by cachedThreadPool)
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++)
            executorService1.execute(new Task());

        //3rd type(by ScheduledThreadPool)
        ScheduledExecutorService  executorService2 = Executors.newScheduledThreadPool(10);

        //task to run after 10 seconds delay
        executorService2.schedule(new Task() , 10 , TimeUnit.SECONDS);
        //task to run repeatedly after 15 seconds(first waits for 10 sec then trigger the task after every 15 sec)
        executorService2.scheduleAtFixedRate(new Task() , 10 , 15 , TimeUnit.SECONDS);
        //task to run repeatedly after 15 sec after prev task completes(first waits for 10 sec , then complete the task and after completing  it waits for 5 sec and then executes another task.
        executorService2.scheduleWithFixedDelay(new Task() , 10 , 15 , TimeUnit.SECONDS);

        //4th type(singleThreadExecutor)
          ExecutorService  executorService3 = Executors.newSingleThreadExecutor();
        for (int i=0;i<10;i++)
            executorService3.execute(new Task());

    }

     static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("inside runnable " + Thread.currentThread().getName());
        }
    }
}
