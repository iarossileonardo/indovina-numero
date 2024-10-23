package client.clientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("client partito");
        Socket s0 = new Socket("localhost", 3000); //socker che dice indirizzo e porta del server a cui connetersi

        BufferedReader in = new BufferedReader(new InputStreamReader(s0.getInputStream())); //stream dati in
        DataOutputStream out = new DataOutputStream(s0.getOutputStream()); //stream dati out

        Scanner input = new Scanner(System.in); //scanner input da tastiera
        String tentativo;
        String sM;
        boolean rigioca = false;

        do {
            do {
                System.out.println("Inserire un numero");
                tentativo = input.nextLine(); //prendo la linea in input con lo scanner

                out.writeBytes(tentativo + "\n"); //invio i dati al server
                
                sM = in.readLine(); //leggo i dati che arrivano dal server
    
                if (sM.equals("<")) {
                    System.out.println("Il numero è troppo piccolo");
                }
                else if (sM.equals(">")) {
                    System.out.println("Il numero è troppo grande");
                }
                else if (sM.equals("!")){
                    System.out.println("Inserire un numero da 0 a 99");
                }
                else {
                    System.out.println("Hai indovinato in " + in.readLine() + " tentativi!");
                    break;
                }

            } while (!sM.equals("=")); //se scrivo "=" esco dal ciclo e si chiude il client

            System.out.println("Vuoi giocare? (s/n)\n");
            String ris = input.nextLine();
            if (ris.equals("s")) {
                rigioca = true;
                out.writeBytes("1\n");
            } else {
                rigioca = false;
                out.writeBytes("0\n");
            }

        } while (rigioca);
        
        input.close();
        s0.close();
        System.out.println("client terminato");
    }
}