package Concurrency.intro;

public class RunnableThread_1 implements Runnable
{
    private Thread thread;
    private String threadName;

    RunnableThread_1(String name)
    {
        threadName = name;
        System.out.println("Creating thread " + threadName);
    }

    @Override
    public void run()
    {
        System.out.println("Running thread " + threadName);

        try
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.println("Thread " + threadName + ", " + i + " " + System.currentTimeMillis());
                thread.wait();
            }
        }
        catch (Exception e)
        {
            System.out.println("Thread " + threadName + " interrupted - " + e.getMessage());
        }

        System.out.println("Thread " + threadName + " ending");
    }

    public void start()
    {
        System.out.println("Starting " + threadName);
        if (thread == null)
        {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
