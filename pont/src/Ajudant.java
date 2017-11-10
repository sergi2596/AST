
import java.io.IOException;
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
public class Ajudant extends Thread {

    private Pont p;
    private Socket s;

    public Ajudant(Socket s, Pont p) {
        this.p = p;
        this.s = s;
    }

    public void run() {
        int op;
        try {
            op = s.getInputStream().read();
            int sen = s.getInputStream().read();
            boolean sentit;
            if (sen == 0) {
                sentit = false;
            } else {
                sentit = true;
            }
            if (op == 1) {
                p.entrar(sentit);
            } else if (op == 2) {
                p.sortir();
            }
        } catch (IOException ex) {
            Logger.getLogger(Ajudant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ajudant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
