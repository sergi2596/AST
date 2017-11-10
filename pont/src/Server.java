
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arnau
 */
public class Server extends Thread {
    Pont p;
    ServerSocket ss;
    public Server() throws IOException{
        ss=new ServerSocket(120,10000);
        p = new Pont();
    }
    @Override
    public void run(){
        while(true){
            Socket s;
            try {
                s = ss.accept();
                new Ajudant(s,p).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
    
    public static void main(String args[]) throws IOException{
      new Thread(new Server()).start();
    }
}