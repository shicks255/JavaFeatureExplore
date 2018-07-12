package Concurrency.conditionAndLock;

public class MainWithCondition
{
    public static void main(String[] args)
    {
        Person steve = new Person(10, "Steven");

//        while(true)
        for (int i = 0 ; i < 25; i++)
        {
            RunnableRandomAge run = new RunnableRandomAge(steve);
            Thread thread = new Thread(run);
            thread.start();
        }


        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
//
        System.out.println(steve.age);
        System.out.println(steve.ages);
        System.out.println(steve.ages.size());

    }

}
