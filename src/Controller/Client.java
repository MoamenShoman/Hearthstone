package Controller;

// A Java program for a Client
import exceptions.FullHandException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Client
{
    // initialize socket and input output streams
    private Socket socket		 = null;
    private ObjectInputStream input = null;
    private ObjectOutputStream out	 = null;


    public class Message implements Serializable{
        private final String text;

        public Message(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    // constructor to put ip address and port
    public Client(String address, int port) throws CloneNotSupportedException, LineUnavailableException, IOException, FontFormatException, FullHandException, UnsupportedAudioFileException {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new ObjectInputStream(System.in);

            // sends output to the socket
            out = new ObjectOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

       /* // keep reading until "Over" is input
        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
*/      ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("Hello from the other side!"));
        messages.add(new Message("How are you doing?"));
        messages.add(new Message("What time is it?"));
        messages.add(new Message("Hi hi hi hi."));
       out.writeObject(messages);

        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[]) throws CloneNotSupportedException, LineUnavailableException, IOException, FontFormatException, FullHandException, UnsupportedAudioFileException {
        Client client = new Client("197.49.244.165", 6000);
    }
}

