package main.java.ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableImp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService= Executors.newFixedThreadPool(10);
        //submit one task
     //   Future<Integer> ans = executorService.submit(new Test());

        //submit multiple task
        List<Future> allFuture =new ArrayList<>();
        for (int i=0;i<15 ;i++) {
            Future<Integer> ans = executorService.submit(new Test());
            allFuture.add(ans);
        }
        System.out.println(allFuture);
        //print
        for (int j=0;j<allFuture.size();j++){
            Future<Integer> future = allFuture.get(j);
            System.out.print(future.get() +" ");
        }

    }
}

class Test implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return 13;
    }
}