
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arnau
 */
public class Pont {
    ReentrantLock lk;
    Condition c;
    boolean sentit; // true dreta, false esquerra
    int numcotxes;
    
    public Pont(){
        lk=new ReentrantLock();
        c=lk.newCondition();
        sentit=true;
        numcotxes=0;
    }
    
    public void entrar(boolean sentit) throws InterruptedException{
        lk.lock();
        try {
            while(sentit != this.sentit && numcotxes>0){
                c.await();
            }
            this.sentit=sentit;
            numcotxes++;
        } finally {
            lk.unlock();
        }
    }
    
    public void sortir(){
        lk.lock();
        try {
            numcotxes--;
            if(numcotxes==0) c.signalAll();
        } finally {
            lk.unlock();
        }
    }
    
}
