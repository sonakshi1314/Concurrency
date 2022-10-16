package main.java.JavaMemoryModel;

public class MyRunnable implements Runnable {
    int count =0;
    @Override
    public void run() {

        for (int i=0;i<10;i++)
            count++;
        System.out.println(Thread.currentThread().getName() +" : "+ count);
    }
}
