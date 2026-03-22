package model;

/**
 * Representa um campeonato de futebol com até 8 clubes.
 * Demonstra: encapsulamento, construtor padrão e sobrecarregado.
 */
public class Campeonato {
    public static final int MAX_CLUBES  = 8;
    public static final int MAX_PARTIDAS = 56; // máximo de combinações possíveis

    private String nome;
    private Clube[] clubes;
    private int quantidadeClubes;
    private Partida[] partidas;
    private int quantidadePartidas;

    // Construtor padrão
    public Campeonato() {
        this.nome = "";
        this.clubes  = new Clube[MAX_CLUBES];
        this.partidas = new Partida[MAX_PARTIDAS];
        this.quantidadeClubes  = 0;
        this.quantidadePartidas = 0;
    }

    // Construtor sobrecarregado
    public Campeonato(String nome) {
        this();
        this.nome = nome;
    }

    // Getters e Setters (encapsulamento)
    public String getNome()             { return nome; }
    public int getQuantidadeClubes()    { return quantidadeClubes; }
    public int getQuantidadePartidas()  { return quantidadePartidas; }
    public void setNome(String nome)    { this.nome = nome; }

    public Clube[] getClubes() {
        Clube[] copia = new Clube[quantidadeClubes];
        for (int i = 0; i < quantidadeClubes; i++) copia[i] = clubes[i];
        return copia;
    }

    public Partida[] getPartidas() {
        Partida[] copia = new Partida[quantidadePartidas];
        for (int i = 0; i < quantidadePartidas; i++) copia[i] = partidas[i];
        return copia;
    }

    public boolean adicionarClube(Clube clube) {
        if (quantidadeClubes >= MAX_CLUBES) return false;
        if (clubeJaCadastrado(clube)) return false;
        clubes[quantidadeClubes++] = clube;
        return true;
    }

    public boolean adicionarPartida(Partida partida) {
        if (quantidadePartidas >= MAX_PARTIDAS) return false;
        partidas[quantidadePartidas++] = partida;
        return true;
    }

    public boolean clubeJaCadastrado(Clube clube) {
        for (int i = 0; i < quantidadeClubes; i++) {
            if (clubes[i] == clube) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nome + " | Clubes: " + quantidadeClubes + "/" + MAX_CLUBES
             + " | Partidas: " + quantidadePartidas;
    }
}
