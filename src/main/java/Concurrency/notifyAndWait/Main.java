package Concurrency.notifyAndWait;

public class Main
{
    public static void main(String[] args)
    {
        Interrogation interrogation = new Interrogation();
        Thread t1 = new Thread(new ThreadQuestion(interrogation));
        Thread t2 = new Thread(new ThreadAnswer(interrogation));

        t1.start();
        t2.start();
    }

    public static class Interrogation
    {
        boolean flag = false;

        public synchronized void question(String msg)
        {
            if (flag)
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(msg);
            flag = true;
            notify();
        }

        public synchronized void answer(String msg)
        {
            if (!flag)
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(msg);
            flag = false;
            notify();
        }
    }
}
