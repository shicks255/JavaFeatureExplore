package IOStuff.streamsAndReaders;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ByteStreams
{

    public static void main(String[] args)
    {
        try(FileInputStream in = new FileInputStream("src/main/java/IOStuff/streamsAndReaders/test.txt"))
        {
            try (FileOutputStream out = new FileOutputStream("src/main/java/IOStuff/streamsAndReaders/testCopy.txt"))
            {

                int c;
                while ((c = in.read()) != -1)
                {
                    out.write(c);
                }
            }
        } catch (Exception e){}
    }


}
