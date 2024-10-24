package server.clientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class gestioneServizio extends Thread{
    Socket s0;
    int numero;
    Random rand = new Random();
    Dati datiGioco;

    gestioneServizio(Socket s0, Dati datiCondivisi) {
        this.s0 = s0;
        datiGioco = datiCondivisi;
        datiGioco.creaLivello();
    }

    @Override
    public void run() {
        try {
            System.out.println("Un client si è collegato alla porta "+ s0.getPort() +" dove risiede il thread " + Thread.currentThread().getName());

            BufferedReader in = new BufferedReader(new InputStreamReader(s0.getInputStream())); // stream dati in entrata
            DataOutputStream out = new DataOutputStream(s0.getOutputStream()); // stream dati inn uscita
            String dati;
            int guess;
            int tentativi = 0;
            boolean indovinato = false;
            String rigioca;
            int index = 0;
            numero = datiGioco.getLivelli().get(0).getNumero();
            do {
                do{
                    System.out.println("numero: " + numero + ", index: " + index);
                    tentativi++;
                    dati = in.readLine();
                    guess  = Integer.parseInt(dati);
                    
                    System.out.println("Numero ricevuto: " + guess);
    
                        if (guess > numero) {
                            System.out.println(">");
                            out.writeBytes(">\n");
                        }
                        else if (guess < numero){
                            System.out.println("<");
                            out.writeBytes("<\n");
                        }
                        else if ((guess < 0) || (guess > 99)){
                            tentativi--;
                            out.writeBytes("!\n");
                        }
                        else{
                            System.out.println("=");
                            out.writeBytes("=\n");
                            this.sleep(500);
                            out.writeBytes("" + tentativi + "\n");
                            indovinato = true;
                        }
                    }while(!indovinato);
                    boolean flag = true;
                    String nome;
                    do {
                        nome = in.readLine();

                        flag = true;

                        for (int i = 0; i < datiGioco.getLivelli().get(index).getClassifica().size(); i++) {
                            if (datiGioco.getLivelli().get(index).getClassifica().get(i).getNome().equals(nome)) {
                                out.writeBytes("400" + "\n");
                                flag = false;
                                break;
                            }
                        }

                        if (flag) {
                            out.writeBytes("200" + "\n");
                        }

                        
                    } while (!flag);
                    
                    datiGioco.getLivelli().get(index).inserisciInClassifica(nome, tentativi);

                    sleep(100);

                    for(int i = 0; i < datiGioco.getLivelli().get(index).getClassifica().size(); i++){
                        if (datiGioco.getLivelli().get(index).getClassifica().get(i).getNome().equals(nome)) {
                            out.writeBytes("" + (i + 1) + "\n");
                        }
                    }
                    
                    rigioca = in.readLine();
                    if (rigioca.equals("1")) {
                        indovinato = false;
                        tentativi = 0;
                        if ((datiGioco.getLivelli().size() <= index) || (index == 0)) {
                            System.out.println("creo livello");
                            datiGioco.creaLivello();
                        }
                    numero = datiGioco.getLivelli().get(index).getNumero();
                    index++;
                }
            } while (rigioca.equals("1"));

            System.out.println("ciao client");
        
            s0.close();    
        } catch (Exception e) {

        } 
    }
}
