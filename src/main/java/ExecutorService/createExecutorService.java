package main.java.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class createExecutorService {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("Creating Executor Service");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("creating runnable ");
        Runnable runnable =()-> System.out.println("inside runnable and thread is " + Thread.currentThread().getName());
        System.out.println("submitting the task specified by runnable to executor service ");
        executorService.submit(runnable);
        System.out.println("creating another task");
        Runnable runnable1 =()-> System.out.println("another task is coming " + Thread.currentThread().getName());
        executorService.submit(runnable1);
    }
}

    /*When you run the above program, the program will never exit. You will need to shut it down explicitly since the executor service keeps listening for new tasks.*/