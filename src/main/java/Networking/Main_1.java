package Networking;

import java.io.*;
import java.net.Socket;


/*
* Used to make a connection to a running com.steven.hicks.TestServer object
* */

public class Main_1
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

        String serverName = "127.0.0.1";
        int port = 8585;

        try
        {
            System.out.println("Commnecting to " + serverName + " on port " + port);

            Socket client = new Socket(serverName, port);

            System.out.println("Connected to " + serverName);

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from something");
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            client.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }


    }


}
