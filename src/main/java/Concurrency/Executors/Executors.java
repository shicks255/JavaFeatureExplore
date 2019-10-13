package Concurrency.Executors;

import java.util.concurrent.CompletableFuture;

public class Executors
{

    public static void main(String[] args)
    {

        CompletableFuture<String> fiture = doFuture();

        while (!fiture.isDone())
            System.out.println("waiting...");

        try{
            System.out.println("Finally done, the answer is " + fiture.get());
        }
        catch (Exception e)
        {}
    }

    private static CompletableFuture<String> doFuture()
    {

        CompletableFuture<String> future = new CompletableFuture<>();

        java.util.concurrent.Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {}

            future.complete("Finished");
        });


        return future;
    }


}
