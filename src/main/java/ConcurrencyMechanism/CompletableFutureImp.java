package main.java.ConcurrencyMechanism;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureImp {
    public static void main(String[] args) {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello").thenCombine(CompletableFuture.supplyAsync(() -> " associates! Its thenCombined"), (value1, value2) -> value1 + value2);
        f1.thenAccept(System.out::println);
    }
}
