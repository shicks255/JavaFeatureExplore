package Concurrency.notifyAndWait;

public class ThreadQuestion implements Runnable
{
    Main.Interrogation interrogation;
    String[] s1 = {"HI", "How are you?", "I am also good", "goodbye"};

    public ThreadQuestion(Main.Interrogation interrogation)
    {
        this.interrogation = interrogation;
    }

    @Override
    public void run()
    {
        for (int i = 0; i<s1.length; i++)
            interrogation.question(s1[i]);
    }
}
