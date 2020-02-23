import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {
    Socket socket;
    DataInputStream input;
    DataOutputStream output;

    public ClientConnection(Socket socket, Client client){
this.socket = socket;

    }
    public void sendMessagetoServer(String data){
        try {
            output.writeUTF(data);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }


    }
    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            while (true) {
                try {
                    while (input.available() == 0) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String reply = input.readUTF();
                    System.out.println(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                    close();
                }
            }} catch(IOException e){
                e.printStackTrace();
                close();
            }

        }


public void close(){
        try{
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

}

