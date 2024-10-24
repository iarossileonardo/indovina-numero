package server.clientserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Livelli {
    private int numero;
    private ArrayList<Giocatore> classifica;
    Random rand = new Random();
    
    
    public Livelli() {
        numero = rand.nextInt(99);
        classifica = new ArrayList<Giocatore>();
    }

    public void inserisciInClassifica(String nome, int tentativi){
        Giocatore nGio = new Giocatore(nome, tentativi);
        getClassifica().add(nGio);
        ordinaClassificaPerTentativi();
    }

    public void ordinaClassificaPerTentativi() {
        Collections.sort(classifica, new Comparator<Giocatore>() {
            @Override
            public int compare(Giocatore g1, Giocatore g2) {
                return Integer.compare(g1.getTentativi(), g2.getTentativi());
            }
        });
    }


    public int getNumero(){
        return numero;
    }

    public ArrayList<Giocatore> getClassifica(){
        return classifica;
    }
    
}
