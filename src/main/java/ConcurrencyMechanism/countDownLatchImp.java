package main.java.ConcurrencyMechanism;

import java.util.concurrent.CountDownLatch;
/*
* A synchronization aid that allows one or more threads to wait until a set of operations being performed in other
* threads completed .
* */
public class countDownLatchImp {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("main method started and the thread executed is " + Thread.currentThread().getName());

        //3 services
        CountDownLatch countDownLatch =new CountDownLatch(3);

        Thread t1=new Thread(new Task(countDownLatch , 3));
        Thread t2=new Thread(new Task(countDownLatch ,8));
        Thread t3 =new Thread(new Task(countDownLatch ,8));
        System.out.println("threads are going to start");
        t1.start();t2.start();t3.start();
        System.out.println("threads started");

        countDownLatch.await(); // it blocks the main thread

        System.out.println("main method ended and the thread executed is " + Thread.currentThread().getName());

    }

    static class  Task implements Runnable{

        private CountDownLatch countDownLatch;
        private int waitTime;

        public Task(CountDownLatch countDownLatch, int waitTime) {
            this.countDownLatch = countDownLatch;
            this.waitTime = waitTime;
        }

        @Override
        public void run() {
            System.out.println("task started and thread executed is "+ Thread.currentThread().getName());
            try {
                System.out.println("Thread is "+ Thread.currentThread().getName() +" and its wait time is "+waitTime);
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task ended and thread executed is "+ Thread.currentThread().getName());
            countDownLatch.countDown();
           }
        }

}
