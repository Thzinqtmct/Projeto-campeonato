package model;

/**
 * Representa um grupo de apostas com até 5 participantes.
 * Demonstra: encapsulamento, construtor padrão e sobrecarregado.
 */
public class Grupo {
    public static final int MAX_PARTICIPANTES = 5;

    private String nome;
    private Campeonato campeonato;
    private Participante[] participantes;
    private int quantidadeParticipantes;

    // Construtor padrão
    public Grupo() {
        this.participantes = new Participante[MAX_PARTICIPANTES];
        this.quantidadeParticipantes = 0;
    }

    // Construtor sobrecarregado
    public Grupo(String nome, Campeonato campeonato) {
        this();
        this.nome       = nome;
        this.campeonato = campeonato;
    }

    // Getters e Setters (encapsulamento)
    public String       getNome()                  { return nome; }
    public Campeonato   getCampeonato()             { return campeonato; }
    public int          getQuantidadeParticipantes(){ return quantidadeParticipantes; }

    public void setNome(String nome)               { this.nome = nome; }
    public void setCampeonato(Campeonato c)         { this.campeonato = c; }

    public Participante[] getParticipantes() {
        Participante[] copia = new Participante[quantidadeParticipantes];
        for (int i = 0; i < quantidadeParticipantes; i++) copia[i] = participantes[i];
        return copia;
    }

    /** Adiciona um participante ao grupo. Retorna false se cheio ou duplicado. */
    public boolean adicionarParticipante(Participante participante) {
        if (quantidadeParticipantes >= MAX_PARTICIPANTES) return false;
        for (int i = 0; i < quantidadeParticipantes; i++) {
            if (participantes[i] == participante) return false;
        }
        participantes[quantidadeParticipantes++] = participante;
        return true;
    }

    // exibe a classificacao completa do grupo - sobrecarga sem parametro
    public void exibirClassificacao() {
        exibirClassificacao(quantidadeParticipantes);
    }

    // exibe apenas os primeiros 'limite' colocados - sobrecarga com parametro
    public void exibirClassificacao(int limite) {
        System.out.println("\n+----------------------------------------------+");
        System.out.println("|  CLASSIFICACAO - Grupo: " + nome);
        System.out.println("|  Campeonato: " + (campeonato != null ? campeonato.getNome() : "N/A"));
        System.out.println("+----------------------------------------------+");

        if (quantidadeParticipantes == 0) {
            System.out.println("|  Nenhum participante cadastrado.");
            System.out.println("+----------------------------------------------+");
            return;
        }

        // ordena por pontos - bubble sort decrescente
        Participante[] ordenados = getParticipantes();
        for (int i = 0; i < ordenados.length - 1; i++) {
            for (int j = 0; j < ordenados.length - 1 - i; j++) {
                if (ordenados[j].getTotalPontos() < ordenados[j + 1].getTotalPontos()) {
                    Participante temp = ordenados[j];
                    ordenados[j] = ordenados[j + 1];
                    ordenados[j + 1] = temp;
                }
            }
        }

        // limita quantos mostrar
        int mostrar = limite;
        if (mostrar > ordenados.length) {
            mostrar = ordenados.length;
        }

        System.out.printf("|  %-4s %-22s %-8s%n", "Pos.", "Participante", "Pontos");
        System.out.println("+----------------------------------------------+");
        for (int i = 0; i < mostrar; i++) {
            System.out.printf("|  %-4d %-22s %-8d%n",
                    (i + 1), ordenados[i].getNome(), ordenados[i].getTotalPontos());
        }
        System.out.println("+----------------------------------------------+");
    }

    @Override
    public String toString() {
        return nome + " | Campeonato: "
             + (campeonato != null ? campeonato.getNome() : "N/A")
             + " | Participantes: " + quantidadeParticipantes + "/" + MAX_PARTICIPANTES;
    }
}
