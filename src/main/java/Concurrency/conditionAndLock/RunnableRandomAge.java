package Concurrency.conditionAndLock;

public class RunnableRandomAge implements Runnable
{
    Person person;

    public RunnableRandomAge(Person person)
    {
        this.person = person;
    }

    public void run()
    {
        person.randomAge();
    }

}
