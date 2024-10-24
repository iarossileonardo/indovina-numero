package server.clientserver;

import java.util.ArrayList;
import java.util.Random;

public class Livelli {
    private int numero;
    private ArrayList<Giocatore> classifica;
    Random rand = new Random();
    
    
    public Livelli() {
        numero = rand.nextInt(99);
        classifica = new ArrayList<Giocatore>();
    }

    public void inserisciInClassifica(){
        //TODO
    }

    public int getNumero(){
        return numero;
    }

    public ArrayList<Giocatore> getClassifica(){
        return classifica;
    }
    
}
