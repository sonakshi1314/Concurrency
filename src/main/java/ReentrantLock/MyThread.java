package main.java.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread{
    ReentrantLock lock=null;
    Shared shared=null;
    Condition inc=null;
    Condition dec=null;
    public MyThread(String name, ReentrantLock lock, Shared shared, Condition inc, Condition dec) {
        //both threads are working on same lock , shared obj , inc ,dec
        super(name);
        this.dec=dec;
        this.inc=inc;
        this.lock=lock;
        this.shared=shared;
    }

    @Override
    public void run() {
        if(Thread.currentThread().getName()=="t1") {
            try {
                increase();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(Thread.currentThread().getName()=="t2") {
            try {
                decrease();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void decrease() throws InterruptedException {

        lock.lock();
        System.out.println(Thread.currentThread().getName() +" got the lock");
        while (shared.currentVal() <=99){
            if(shared.currentVal()==0){
                System.out.println(Thread.currentThread().getName()+" releasing the lock without calling unlock method");
                dec.await();
                System.out.println(Thread.currentThread().getName()+" got the lock without calling lock method");
            }
            shared.sub();
            System.out.println(Thread.currentThread().getName()+" decrease the value of i" + shared.currentVal());
            if(shared.currentVal()==0){
                System.out.println(Thread.currentThread().getName()+ "signal other");
                inc.signalAll();
            }
        }
        lock.unlock();
        System.out.println(Thread.currentThread().getName()+ "Released the lock");
    }

    private void increase() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() +" got the lock");
        while (shared.currentVal() <=99){
            if(shared.currentVal()==20){
                System.out.println(Thread.currentThread().getName()+" releasing the lock without calling unlock method");
                inc.await();
                System.out.println(Thread.currentThread().getName()+" got the lock without calling lock method");
            }
            shared.add();
            System.out.println(Thread.currentThread().getName()+" increase the value of i" + shared.currentVal());
              if(shared.currentVal()==20){
                  System.out.println(Thread.currentThread().getName()+ "signal other");
                  dec.signalAll();
              }
        }
        lock.unlock();
        System.out.println(Thread.currentThread().getName()+ "Released the lock");
    }
}
