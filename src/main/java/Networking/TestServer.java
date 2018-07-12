package Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TestServer implements Runnable
{
    private Thread thread;
    private ServerSocket serverSocket;

    public TestServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run()
    {
        while (true)
        {
            try
            {
                System.out.println("waiting for client on port " + serverSocket.getLocalPort());
                Socket server = serverSocket.accept();

                System.out.println("just connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("Thank you for connecting");

                server.close();
            }
            catch (SocketTimeoutException e)
            {
                System.out.println(e);
            }
            catch (IOException e)
            {
                System.out.println(e);
                break;
            }
        }
    }

    public void start()
    {
        if (thread == null)
        {
            thread = new Thread(this);
            thread.start();
        }
    }
}
