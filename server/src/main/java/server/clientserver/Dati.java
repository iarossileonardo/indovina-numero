package server.clientserver;

import java.util.ArrayList;
import java.util.Random;

public class Dati {
    private ArrayList<Livelli> livelli = new ArrayList<Livelli>();
    Random rand = new Random();

    public void creaLivello(){
        livelli.add(new Livelli());
    }

    public ArrayList<Livelli> getLivelli() {
        return livelli;
    }
}
