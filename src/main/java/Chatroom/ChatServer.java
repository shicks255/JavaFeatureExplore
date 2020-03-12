package Chatroom;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer
{
    JFrame frame = new JFrame("ChatterServer");
    JTextField jTextField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);

    private static int PORT;
    private static String serverName;

    private static HashSet<String> names = new HashSet<>();
    private static HashSet<PrintWriter> writers = new HashSet<>();

    public ChatServer()
    {
        jTextField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(jTextField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();
    }

    public String getPORT()
    {
        return JOptionPane.showInputDialog(frame,
                "Choose a port for the server to run on.",
                "8585");
    }

    public String getName()
    {
        return JOptionPane.showInputDialog(frame,
                "Choose a name for the server.",
                "SteveChat");
    }

    public static void main(String[] args) throws Exception
    {
        ChatServer server = new ChatServer();
        server.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        server.frame.setVisible(true);

        PORT = Integer.valueOf(server.getPORT());
        serverName = server.getName();

        server.jTextField.setText("Chat server is running on port " + PORT);

        System.out.println("Chat server is running");
        ServerSocket listener = new ServerSocket(PORT);
        try
        {
            while (true)
                new Handler(listener.accept(), server.messageArea).start();
        }
        finally
        {
            listener.close();
        }
    }

    private static class Handler extends Thread
    {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private JTextArea textArea;

        public Handler(Socket socket, JTextArea messageArea)
        {
            this.socket = socket;
            textArea = messageArea;
        }

        public void run()
        {
            try
            {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true)
                {
                    out.println("Screen name");
                    name = in.readLine();
                    if (name == null)
                    {
                        return;
                    }
                    synchronized (names)
                    {
                        if (!names.contains(name))
                        {
                            names.add(name);
                            break;
                        }
                    }
                }

                out.println("Name accepted");
                writers.add(out);

                for (PrintWriter writer : writers)
                    writer.println("Message " + name + ": " + "Welcome to " + serverName + "!");
                textArea.append("Message " + name + ": " + "Welcome to " + serverName + "!" + "\r\n");

                while (true)
                {
                    String input = in.readLine();
                    if (input == null)
                        return;
                    for (PrintWriter writer : writers)
                        writer.println("Message " + name + ": " + input);
                    textArea.append("Message " + name + ": " + input + "\r\n");
                }

            } catch (IOException e)
            {
                System.out.println(e);
            }
            finally
            {
                if (name != null)
                    names.remove(name);
                if (out != null)
                    writers.remove(out);

                try
                {
                    socket.close();
                }
                catch (IOException e)
                {

                }
            }
        }
    }
}
