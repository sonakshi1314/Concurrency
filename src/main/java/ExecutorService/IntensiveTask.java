package main.java.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntensiveTask {
    public static void main(String[] args) {
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        //submit the task for execution (CPU)
        for (int i=0;i<10;i++)
            executorService.submit(new cpuIntensive());

        executorService.shutdown();

        //submit the task for execution (IO)

        ExecutorService executorServiceForIO = Executors.newFixedThreadPool(100);
        for (int i=0;i<10;i++)
            executorServiceForIO.submit(new IOIntensive());

        executorServiceForIO.shutdown();
    }
}

//cpu intensive task (algo to create hash , crypography)
 class  cpuIntensive implements Runnable{

    @Override
    public void run() {
        //some CPU task
        System.out.println("CPU task is running on " + Thread.currentThread().getName() + " thread");
    }
}

//some IO intensive task (related to DB , HTTP call , network call )
class IOIntensive implements Runnable{
    @Override
    public void run() {
        //some IO task
        System.out.println("IO task is running on " + Thread.currentThread().getName() + " thread");
    }
}


/*
Pool size depends on Task Type
*    Task Type -> IF CPU intensive --> ideal pool size (CPU core count)
*  Task Type -> If IO intensive -->ideal pool size (High) as in IO task thread gets  blocked during teh operations but  then also we have some threads that can execute some other task .
* */

