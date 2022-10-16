package main.java.JavaMemoryModel;

//JMM is a specification which guarantees visibility of fields (aka happens before ) amidst reordering of instructions(It is done to incr the performance).
/*If we don't put volatile keyword then there can be visibility problem .as if one thread is updating the value it will not flush out in main memory and hence there is no guarantee that  second thread will read the correct value only . Hence, by putting volatile keyword any updated value is pushed in RAM , so that if any other thread has to access it , it will get correct response always . It can also be done by lock and synchronized block or keyword.*/
public class FieldVisibility {

     static volatile int  x=0;

     static int readVal(){
         return x;
     }

    static void  writeVal(){
         x=100;
     }

    public static void main(String[] args) {

         Runnable runnable=()->{
             System.out.println("inside runnable");
             System.out.println(Thread.currentThread().getName() );
             writeVal();
             int x=  readVal();
             System.out.println(x);
         };

         Thread t1=new Thread(runnable);
         t1.start();
         Thread t2=new Thread(runnable);
         t2.start();
    }
}


