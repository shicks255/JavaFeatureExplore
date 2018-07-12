package Networking;

import java.io.IOException;


/*
* Used to start the com.steven.hicks.TestServer class on port 8585
* */

public class MainServerStart
{
    public static void main(String[] args)
    {
        try
        {
            TestServer server = new TestServer(8585);
            server.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
