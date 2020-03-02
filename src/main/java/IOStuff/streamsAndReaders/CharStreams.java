package IOStuff.streamsAndReaders;

import java.io.FileReader;
import java.io.FileWriter;

public class CharStreams
{

    public static void main(String[] rags)
    {
        try (
                FileReader in = new FileReader("src/main/java/IOStuff/streamsAndReaders/test.txt");
                FileWriter out = new FileWriter("src/main/java/IOStuff/streamsAndReaders/testCopy.txt"))
        {

            int c;
            while((c = in.read()) != -1)
            {
                out.write(c);
            }

        }
        catch (Exception e)
        {}
    }
}
