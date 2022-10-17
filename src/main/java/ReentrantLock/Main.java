package main.java.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private  static final ReentrantLock lock =new ReentrantLock();

    private  static void accessResource(){
        lock.lock();
        //access the shared resource
        try {
            System.out.println("thread locked is " + Thread.currentThread().getName());
            accessResource();
        }
        finally{
            lock.unlock();
        }

    }
    public static void main(String[] args) {

        Thread t1=new Thread(Main::accessResource); // by method reference
        Thread t2=new Thread(Main::accessResource);
        Thread t3=new Thread(Main::accessResource);
        t1.start();
        t2.start();
        t3.start();
    }

}

/*
* If thread t1 has the lock , then rest of threads will go to the waiting state and once the lock is
* released , rest of the threads will go to the runnable state. Same as synchronized but with more features.
* Let suppose in shared resource there is an exception and if we are not handling the exception then that
*  particular lock will never get unlock and hence rest of the threads which were in waiting state will always
* be in waiting state. That is  why it is good practice to put lock .unlock() in finally block and shared
* resource in try catch.
*
* why name is given as ReentrantLock ?
* As if somehow there is a recursive call in shared resource , so everytime  thread will also hit lock.lock();
* and try to have a access , which this thread already has . As it again and again calling lock.lock() that is why
* it is called ReentrantLock, but internally it will only increase the count of locks .
*
* fairLock --> ReentrantLock lock =new ReentrantLock(true); fair chance is given i.e on basis of first come ,
* first served. Hence , less starvation less but it will slow the application.
* Unfair--> ReentrantLock lock =new ReentrantLock(false); There is no gareenty that it will see waiting queue
* and give opportunity to that threads , which ever threads come at that time , that thread will get the
* chance . Hence , it is faster but there can be starvation .
* */
