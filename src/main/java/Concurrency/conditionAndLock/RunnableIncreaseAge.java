package Concurrency.conditionAndLock;

public class RunnableIncreaseAge implements Runnable
{

    Person person;

    public RunnableIncreaseAge(Person person)
    {
        this.person = person;
    }

    public void run()
    {
        person.increaseAge();
//        System.out.println(person.name + " has increased to " + person.age);
//        System.out.println(person.ages.size());
    }

}
