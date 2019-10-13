package Concurrency.Executors;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Executors
{

    public static void main(String[] args)
    {
        doSync();
        doAsync();
    }

    private static void doSync()
    {
        long current = System.currentTimeMillis();
        CompletableFuture<String> future = doFuture()
                .thenCompose((f1) -> doFuture())
                .thenCompose((f2) -> doFuture());

        try
        {
            System.out.println(future.get());
        } catch (Exception e)
        {}
        System.out.println("Finished synchronously in " + (System.currentTimeMillis() - current));
    }

    private static void doAsync()
    {
        long current = System.currentTimeMillis();

//        CompletableFuture<Void> allFutures =
//                CompletableFuture.allOf(
//                        doFuture(),
//                        doFuture(),
//                        doFuture()
//                );
//        try{
//            allFutures.get();
//        } catch (Exception e)
//        {}

        String answer = Stream.of(doFuture(), doFuture(), doFuture())
                .map(CompletableFuture::join)
                .collect(Collectors.joining());

        System.out.println("Finished asynchronously in " + (System.currentTimeMillis() - current));
    }

    private static CompletableFuture<String> doFuture()
    {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            try{
                Thread.sleep(1000);
            } catch (Exception e)
            {}
            return "hello";
        });


        return future;
    }


}
