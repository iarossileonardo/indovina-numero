package server.clientserver;

public class Giocatore {
    private String nome;
    private int tentativi;

    public Giocatore(String nome, int tentativi) {
        this.nome = nome;
        this.tentativi = tentativi;
    }

    public String getNome() {
        return nome;
    }

    public int getTentativi() {
        return tentativi;
    }
}
