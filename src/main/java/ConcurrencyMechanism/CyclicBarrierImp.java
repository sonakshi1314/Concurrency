package main.java.ConcurrencyMechanism;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* It is synchronization aid that allows a set of threads to wait for each other to reach a common barrier point . The threads which reach a barrier point has to wait for other threads to reach .As soon as all the threads have reached the barrier point , all of them are released to continue . Cyclic barrier can be reused after the waiting threads are released and that is where it is diff from countdown latch . We can reuse cyclicBarrier by calling reset() which resets the barrier to its initial state .
*
* */
public class CyclicBarrierImp {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CyclicBarrier cyclicBarrier =new CyclicBarrier(4);
        for (int i=0;i<4 ;i++)
        executorService.submit(()-> {
            try {
                new ZooManager().performTask(cyclicBarrier);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();

    }
}

class ZooManager{
    public  void removeLion(){
        System.out.println("Removing Lion and thread name is " + Thread.currentThread().getName());
    }

    public  void cleanCage(){
        System.out.println("Cleaning cage and thread name is " + Thread.currentThread().getName());
    }

    public  void addLion(){
        System.out.println("Adding Lion and thread name is " + Thread.currentThread().getName());
    }

    public  void performTask(CyclicBarrier cyclicBarrier) throws BrokenBarrierException, InterruptedException {
        removeLion();
        cyclicBarrier.await();
        cleanCage();
        cyclicBarrier.await();
        addLion();
        cyclicBarrier.await();
    }
}
