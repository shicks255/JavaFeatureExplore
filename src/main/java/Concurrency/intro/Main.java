package Concurrency.intro;


public class Main
{
    public static void main(String[] args)
    {
        RunnableThread_1 newThread = new RunnableThread_1("FirstThread");
        newThread.start();

        RunnableThread_1 newThread2 = new RunnableThread_1("SecondThread");
        newThread2.start();

//        ExtendedThread extendedThread = new ExtendedThread("com.steven.hicks.threads.intro.ExtendedThread");
//        extendedThread.setPriority(1);
//        extendedThread.start();

        String test = "whereAreWe?";
    }

}
