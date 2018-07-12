package Concurrency.notifyAndWait;

public class ThreadAnswer implements Runnable
{
    Main.Interrogation interrogation;
    String[] s2 = {"Hi", "I am doing pretty good, how are you>", "take care"};

    public ThreadAnswer(Main.Interrogation interrogation)
    {
        this.interrogation = interrogation;
    }

    @Override
    public void run()
    {
        for (int i = 0; i<s2.length; i++)
            interrogation.answer(s2[i]);
    }

}
