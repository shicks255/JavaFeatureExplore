package Concurrency.syncAndSleep;

public class Main_JoinTest
{
//    private static Integer number = 0;

    public static void main(String[] args) throws InterruptedException
    {

//        --------Playing with Syncronization
        Person steve = new Person("Steve", 0);

        long timeStart = System.currentTimeMillis();
        for (int i = 1; i < 10; i++)
        {
            Thread t = new Thread(() ->
            {
                synchronized (steve)
                {
                    System.out.println(steve.incrementAndGetAge());
                    try
                    {
                        System.out.println(Thread.currentThread() + " waiting");
                        steve.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " finished waiting");
                }
            });
            t.start();
        }

        Thread.sleep(10000);
        synchronized (steve)
        {
            steve.notifyAll();
        }


        long timeEnd = System.currentTimeMillis();
        System.out.println(timeEnd - timeStart + " is the time");

//        --------Playing with joining and sleeping
//        RunnableThread_type2 thread1 = new RunnableThread_type2("Thread 1");
//        RunnableThread_type2 thread2 = new RunnableThread_type2("Thread 2");
//
//        Stream<Integer> loop = IntStream.range(1, 100).boxed();
//        loop.forEach(i ->
//        {
//            Thread t = new Thread(new RunnableThread_type2("Thread" + i));
//            t.start();
//            try
//            {
//                t.join();
//                Thread.sleep(1000);
//            }
//            catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        });


//        --------Playing with More joining
//        Thread t1 = new Thread(thread1);
//        t1.setDaemon(true);
//        t1.start();
//
//        Thread t2 = new Thread(thread2);
//        t2.setDaemon(true);
//        t2.start();
//
//        try
//        {
//            This will cause the main method to not finished(to be blocked) until t2 finishes.
//            t2.join();
//            t1.join();
//        }
//        catch (InterruptedException e)
//        {
//            System.out.println("Thread interrupted");
//        }

        System.out.println("Main thread finishing");
    }
}
