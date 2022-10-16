package main.java.ConcurrencyMechanism;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueImp {
    //Shared Queue
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        //Producer Thread
        Thread producerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //Consumer Thread
        Thread consumerThread= new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    private static void producer() throws InterruptedException{
        Random random= new Random();
        while (true) {
            Thread.sleep(2000);
            int produced = random.nextInt(100);
            queue.put(produced);
            System.out.println("Produced:"+produced);
        }
    }

    private static void consumer() throws InterruptedException{
        while (true) {
            Thread.sleep(2000);
            Integer consumed = queue.take();
            System.out.println("Consumed:"+consumed);
        }
    }
}
