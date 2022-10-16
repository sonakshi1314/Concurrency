package main.java.MultiThreading;

public class Main {

    public static void main(String[] args) {
        Thread thread =new Thread();
        thread.start(); // here it will just start and then stops as it is not performing any task .

        //call MyThread class
        MyThread myThread=new MyThread();// creates a new thread
        myThread.start(); // execution starts and it is in runnable state and run method is called
        // and if we dont call start method then main thread will only call run method internally .
        myThread.run();

        //call MyRunnable class
//        Thread thread1=new Thread(new MyRunnable());
//        thread1.start();


        //call Runnable(java8)
        Runnable myRunnable = () -> {
            System.out.println("In runnable interface "+MyThread.currentThread().getName() + " named thread is called");
        };
        Thread thread1 =new Thread(myRunnable , "Sonakshi");
        thread1.start();

    }

}

// Threads can be created by using two mechanisms : 1. extends Thread class  2. implements runnable
//1. extends Thread class

class MyThread extends Thread {

    public void run(){
        System.out.println("Run method is executed");
        System.out.println(Thread.currentThread().getName());
    }

}

//2. implements runnable
//class MyRunnable implements Runnable{
//
//    @Override
//    public void run() {
//        System.out.println("In runnable interface "+MyThread.currentThread().getName());
//    }
//}



