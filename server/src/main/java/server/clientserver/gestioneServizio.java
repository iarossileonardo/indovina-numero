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

    gestioneServizio(Socket s0, int numero) {
        this.s0 = s0;
        this.numero = numero;
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
            do {
                do{
                    System.out.println("numero: " + numero);
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
                            tentativi = 0;
                            indovinato = true;
                        }
                }while(!indovinato);
                rigioca = in.readLine();
                if (rigioca.equals("1")) {
                    numero = rand.nextInt(100);
                }
            } while (rigioca.equals("1"));

            System.out.println("ciao client");
        
            s0.close();    
        } catch (Exception e) {

        } 
    }
}