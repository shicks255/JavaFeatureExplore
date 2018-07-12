package Concurrency.intro;

public class ExtendedThread extends Thread
{
    private Thread thread;
    private String threadName;

    public ExtendedThread( String threadName)
    {
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    public void run()
    {
        System.out.println("Running " + threadName);
        try
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.println("Thread " + threadName + ", " + i  + " " + System.currentTimeMillis());
//                Thread.sleep(50);
            }
        }
        catch (Exception e)
        {
            System.out.println("Thread " + threadName + " was interrupted " + e.getStackTrace());
        }

        System.out.println("Thread " + threadName + " exiting");
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
