package Concurrency.latches;

import java.util.concurrent.CountDownLatch;

public class Main
{

    public static void main(String[] args)
    {

        try {
            System.out.println(threadsStartWhenCreated());
            System.out.println(threadsStartedAtSameTime());
        } catch (InterruptedException e)
        {}

    }

    public static long threadsStartedAtSameTime() throws InterruptedException
    {
        long start = System.currentTimeMillis();
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++)
        {
            Thread t = new Thread(() -> {
                try{
                    startLatch.await();
                    System.out.println("Thread " + Thread.currentThread().getName() + " is sleeping for a second");
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {}

                endLatch.countDown();
            });
            t.start();
        }

        startLatch.countDown();
        endLatch.await();

        return System.currentTimeMillis() - start;
    }

    public static long threadsStartWhenCreated() throws InterruptedException
    {
        long start = System.currentTimeMillis();
        CountDownLatch endLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++)
        {
            Thread t = new Thread(() -> {

                System.out.println("Thread " + Thread.currentThread().getName() + " is sleeping for a second");
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {}

                endLatch.countDown();
            });
            t.start();
        }

        endLatch.await();

        return System.currentTimeMillis() - start;
    }

}
