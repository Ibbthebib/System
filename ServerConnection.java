import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//Multithreaded so extends thread
public class ServerConnection extends Thread {
    Socket socket;
    Server server;
    DataInputStream in;
    DataOutputStream out;

    public ServerConnection(Socket socket, Server server){
        super("ServerConnectionThread");
        this.socket = socket;
        this.server = server;
    }

    public void sendStringToClient(String text) throws IOException {

        try{
            out.writeUTF(text);
            out.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void sendStringtoAllClients(String text) throws IOException{
            for(int i = 0; i < server.connections.size(); i++){
                ServerConnection s = server.connections.get(i);
                s.sendStringToClient(text);
            }
    }

    public void run(){
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while(true){
                while (in.available() == 0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String textIn = in.readUTF();
                sendStringtoAllClients(textIn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
