package server.clientserver;

import java.util.ArrayList;
import java.util.Random;

public class Livelli {
    private ArrayList<Integer> livelli = new ArrayList<Integer>();
    Random rand = new Random();

    public void creaLivello(){
        livelli.add(rand.nextInt(99));
    }

    public ArrayList<Integer> getLivelli() {
        return livelli;
    }

    
}
