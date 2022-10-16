package main.java.JavaMemoryModel;

public class Main {

    public static void main(String[] args) {

        Runnable runnable1= new MyRunnable();
        Runnable runnable2= new MyRunnable();

        Thread t1=new Thread(runnable1 , "first thread");
        Thread t2 =new Thread(runnable2 , "second thread");
        t1.start();
        t2.start();
    }
}

/*As runnables are difference i.e 2 objects are created for MyRunnable class , hence no data is shared so we will get separate output . But if we have only one instance ie Runnable runnable= new MyRunnable() and we pass runnable then it means 2 threads are reading same piece of code and hence count will not get updated correctly as there will be difference in read and update operations . In order to solve this , we can put the critical section inside synchronized block .
  */