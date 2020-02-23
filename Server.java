import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Two different classes : Accepting connections &&
 * handling different connectiongs from everything else.
 */
public class Server {
    ServerSocket ss ;
    Socket s;

ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();

    public Server() {
        try {
            ss = new ServerSocket(3333);

            while(true) {
                s = ss.accept();
                ServerConnection sc = new ServerConnection(s,this);
                sc.start();
                    connections.add(sc);

            }


        } catch (IOException e) {
            e.getStackTrace();
        }

    }
//
//    public void listenForData() throws IOException {
//        while (true) {
//            try {
//                while (input.available() == 0) {
//                    try {
//                        Thread.sleep(1);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            } finally {
//                String dataIn = input.readUTF();
//                output.writeUTF(dataIn);
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
//        new Server(4999);
        new Server();
//        for(String s : args){
//            input = new Scanner(s);
        }
    }

