package Concurrency.syncAndSleep;

public class RunnableThread_type2 implements Runnable
{
    private String threadName;

    public RunnableThread_type2(String threadName)
    {
        this.threadName = threadName;
    }

    public void run()
    {
//        System.out.println("starting thread " + threadName);
        try
        {
//            System.out.println("running " + threadName);
//            Main_JoinTest.number = Main_JoinTest.number + 1;
//            Main_JoinTest.increment();
//            System.out.println(Main_JoinTest.number);

//            Thread.sleep(5000);
//            System.out.println("sleeping finished " + threadName);
        }
        catch (Exception e)
        {
            System.out.println("Interuppted " + threadName + e);
        }
    }
}
