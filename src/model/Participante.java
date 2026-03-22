package model;

// participante que faz apostas, herda de Pessoa
public class Participante extends Pessoa {
    private static final int MAX_APOSTAS = 100;

    private Aposta[] apostas;
    private int quantidadeApostas;

    // Construtor padrão
    public Participante() {
        super();
        this.apostas = new Aposta[MAX_APOSTAS];
        this.quantidadeApostas = 0;
    }

    // Construtor sobrecarregado — chama o construtor da superclasse
    public Participante(String nome, String email, String senha) {
        super(nome, email, senha);
        this.apostas = new Aposta[MAX_APOSTAS];
        this.quantidadeApostas = 0;
    }

    // Getters (encapsulamento)
    public int getQuantidadeApostas() { return quantidadeApostas; }

    public Aposta[] getApostas() {
        Aposta[] copia = new Aposta[quantidadeApostas];
        for (int i = 0; i < quantidadeApostas; i++) copia[i] = apostas[i];
        return copia;
    }

    public boolean adicionarAposta(Aposta aposta) {
        if (quantidadeApostas >= MAX_APOSTAS) return false;
        apostas[quantidadeApostas++] = aposta;
        return true;
    }

    /** Soma a pontuação de todas as apostas encerradas. */
    public int getTotalPontos() {
        int total = 0;
        for (int i = 0; i < quantidadeApostas; i++) {
            total += apostas[i].calcularPontos();
        }
        return total;
    }

    /** Verifica se o participante já apostou em uma partida. */
    public boolean jaApostouPartida(Partida partida) {
        for (int i = 0; i < quantidadeApostas; i++) {
            if (apostas[i].getPartida() == partida) return true;
        }
        return false;
    }

    // Sobreposição (override) de métodos abstratos da superclasse Pessoa
    @Override
    public String getTipo() {
        return "Participante";
    }

    @Override
    public String getInfo() {
        return "=== Participante ===\n"
             + "Nome  : " + getNome() + "\n"
             + "Email : " + getEmail() + "\n"
             + "Pontos: " + getTotalPontos() + "\n"
             + "Apostas registradas: " + quantidadeApostas;
    }
}
