package model;

// aposta com placar exato - 10 pts se acertar placar, 5 pts so o resultado
public class ApostaPlacar extends Aposta {
    private Placar placarPrevisto;

    // Construtor padrão
    public ApostaPlacar() {
        super();
        this.placarPrevisto = new Placar(); // usa construtor padrão de Placar
    }

    // Construtor sobrecarregado — chama o construtor da superclasse
    public ApostaPlacar(Participante participante, Partida partida, Placar placarPrevisto) {
        super(participante, partida, placarPrevisto.getResultado());
        this.placarPrevisto = placarPrevisto;
    }

    // Getter e Setter (encapsulamento)
    public Placar getPlacarPrevisto() { return placarPrevisto; }

    public void setPlacarPrevisto(Placar placarPrevisto) {
        this.placarPrevisto = placarPrevisto;
        setResultadoPrevisto(placarPrevisto.getResultado());
    }

    /**
     * Sobreposição do método abstrato da superclasse Aposta.
     * Retorna 10 pts para placar exato, 5 pts para resultado correto, 0 caso contrário.
     * Comportamento DIFERENTE de ApostaResultado — demonstra polimorfismo.
     */
    @Override
    public int calcularPontos() {
        if (!getPartida().isEncerrada()) return 0;
        Placar real = getPartida().getResultado();
        if (real == null) return 0;

        if (placarPrevisto.equals(real)) {
            return 10; // placar exato
        }
        if (getResultadoPrevisto() == real.getResultado()) {
            return 5;  // apenas resultado correto
        }
        return 0;
    }

    @Override
    public String getTipoAposta() {
        return "Aposta com Placar";
    }

    @Override
    public String getDescricaoPalpite() {
        return placarPrevisto + " (" + getResultadoPrevisto().getDescricao() + ")";
    }
}
