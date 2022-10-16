package main.java.MultiThreading;

public class statesOfThread {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable=()-> System.out.println("Current thread is "+Thread.currentThread().getName());


        Thread thread=new Thread(runnable ,"My first thread ");
        thread.start();
        Thread.sleep(2000);
        System.out.println("after sleep");

        Thread thread2=new Thread(runnable ,"My second thread ");
        thread2.start();


        Runnable runnable1=()-> {
            while (true){
               sleep(1000);
                System.out.println("inside runnable interface for demon testing ");
            }
        };

        Thread thread1=new Thread(runnable1 , "demon testing");
        thread1.setDaemon(true); // by setting demon true thread1 will run till the time main thread is running after that this thread will also get terminated.
        thread1.start();
        sleep(2000);

    }

    private static void sleep(long millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


//Question : why always first thread is executing as it is sleeping for 2sec ,
// at that time second thread can also get executed .