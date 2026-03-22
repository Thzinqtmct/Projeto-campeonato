package model;

// aposta so no resultado (vencedor ou empate) - vale 5 pontos
public class ApostaResultado extends Aposta {

    // Construtor padrão
    public ApostaResultado() {
        super();
    }

    // Construtor sobrecarregado — chama o construtor da superclasse
    public ApostaResultado(Participante participante, Partida partida, Resultado resultadoPrevisto) {
        super(participante, partida, resultadoPrevisto);
    }

    /**
     * Sobreposição do método abstrato da superclasse Aposta.
     * Retorna 5 pontos se acertou o resultado; 0 caso contrário.
     */
    @Override
    public int calcularPontos() {
        if (!getPartida().isEncerrada()) return 0;
        Placar real = getPartida().getResultado();
        if (real == null) return 0;
        return getResultadoPrevisto() == real.getResultado() ? 5 : 0;
    }

    @Override
    public String getTipoAposta() {
        return "Aposta de Resultado";
    }

    @Override
    public String getDescricaoPalpite() {
        return getResultadoPrevisto().getDescricao();
    }
}
