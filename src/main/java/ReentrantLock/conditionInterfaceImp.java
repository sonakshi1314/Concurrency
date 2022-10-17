package main.java.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class conditionInterfaceImp {

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        Condition inc =lock.newCondition();
        Condition dec =lock.newCondition();
        Shared shared=new Shared();
        MyThread t1=new MyThread("t1" , lock ,shared ,inc , dec);
        MyThread t2=new MyThread("t2" , lock ,shared ,inc , dec);
        t1.start();
        t2.start();

    }
}

class Shared{
    private int val=0;
    void add(){
        val++;
    }
    void sub(){
        val--;
    }
    int currentVal(){
        return val;
    }
}