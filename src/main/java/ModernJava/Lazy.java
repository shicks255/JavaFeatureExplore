package ModernJava;

import java.util.function.Supplier;

public class Lazy
{

    public static void main(String[] args)
    {
        Long currentTime = System.currentTimeMillis();
        doWork(expensiveTest("tes"), expensiveTest("test"));
        System.out.println((System.currentTimeMillis() - currentTime) / 1000);

        currentTime = System.currentTimeMillis();
        doWorkLazy(() -> expensiveTest("tes"), () -> expensiveTest("test"));
        System.out.println((System.currentTimeMillis() - currentTime) / 1000);
    }

    public static void doWork(boolean a, boolean b)
    {
        if (a && b)
            System.out.println("both passed");
        else
            System.out.println("failed");
    }

    public static void doWorkLazy(Supplier<Boolean> a, Supplier<Boolean> b)
    {
        if (a.get() && b.get())
            System.out.println("Both passed");
        else
            System.out.println("failed");
    }

    public static boolean expensiveTest(String arg)
    {
        try
        {
            System.out.println("Starting expensive work for " + arg);
            Thread.sleep(5000);
            return arg.equalsIgnoreCase("test");
        }
        catch (InterruptedException e)
        {}
        return false;
    }

}
