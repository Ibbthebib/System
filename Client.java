import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Socket s;
    DataInputStream input;
    DataOutputStream output;
    ClientConnection client;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        try {
            s = new Socket("localhost", 3333);
            client = new ClientConnection(s, this);
            client.start();
            System.out.println("New client is connected");
            input = new DataInputStream(s.getInputStream());
            output = new DataOutputStream(s.getOutputStream());

            listenForInput();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void listenForInput() {
        Scanner console = new Scanner(System.in);

        while (true) {
            while (!console.hasNextLine()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String input = console.nextLine();

            if (input.toLowerCase().equals("quit")) {
                client.close();
                break;
            }
            client.sendMessagetoServer(input);
        }
        client.close();
    }

}
