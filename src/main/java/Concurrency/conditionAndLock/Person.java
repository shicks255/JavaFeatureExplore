package Concurrency.conditionAndLock;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Person
{
    int age;
    String name;

    private ReentrantLock lock = new ReentrantLock();

    private Condition ageCondition;

    //after each increase age, we're goign to add the ages to this set,
    //and see if the amount of ages matches the amount of iterations
    Set<Integer> ages;

    public Person(int age, String name)
    {
        this.age = age;
        this.name = name;
        ages = new HashSet<>();

        ageCondition = lock.newCondition();
    }


    public void increaseAge()
    {
        lock.lock();
        try
        {
            System.out.println("we are goign to be increasing the age now");
            System.out.println("Current age = " + age);
            age++;
            System.out.println("increasing to " + age);
            ages.add(age);
        }
        finally
        {
            lock.unlock();
        }
    }

    public void randomAge()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        if (randomNumber > 9)
            increaseAge();
        if (randomNumber <=8 )
            decreaseAge();
    }

    public void increaseAgeToGetOutOfWaitCondition()
    {
        age += 15;
        ageCondition.signalAll();
        System.out.println("increasing AGE TO SAVE US");
    }

    public void decreaseAge()
    {
        lock.lock();
        try
        {
            System.out.println("Current age = " + age);
            age--;
            System.out.println("decreasing to " + age);
            System.out.println(Thread.currentThread().getName());
            ages.add(age);
            ageCondition.signalAll();

            if (age <= 0)
            {
                try
                {
                    increaseAgeToGetOutOfWaitCondition();
                    ageCondition.await();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }
        finally
        {
            lock.unlock();
        }
    }


    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
