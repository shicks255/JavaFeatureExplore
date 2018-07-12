package Concurrency.conditionAndLock;

public class Main
{

    public static void main(String[] args) throws InterruptedException
    {

        Person steve = new Person(28, "Steven");

        for (int i = 0; i < 10; i++)
        {
            RunnableIncreaseAge run = new RunnableIncreaseAge(steve);
            Thread thread = new Thread(run);
            thread.start();
        }


        Thread.sleep(2000);

        String test = "Test";
        System.out.println(steve.age);
        System.out.println(steve.ages);
        System.out.println(steve.ages.size());

    }

}
