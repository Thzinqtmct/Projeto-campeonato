package model;

// classe base para os tipos de aposta
public abstract class Aposta {
    private Participante participante;
    private Partida partida;
    private Resultado resultadoPrevisto;

    // Construtor padrão
    public Aposta() {}

    // Construtor sobrecarregado
    public Aposta(Participante participante, Partida partida, Resultado resultadoPrevisto) {
        this.participante      = participante;
        this.partida           = partida;
        this.resultadoPrevisto = resultadoPrevisto;
    }

    // Getters e Setters (encapsulamento)
    public Participante getParticipante()    { return participante; }
    public Partida      getPartida()         { return partida; }
    public Resultado    getResultadoPrevisto(){ return resultadoPrevisto; }

    public void setParticipante(Participante p)  { this.participante = p; }
    public void setPartida(Partida partida)       { this.partida = partida; }
    public void setResultadoPrevisto(Resultado r) { this.resultadoPrevisto = r; }

    /**
     * Calcula os pontos obtidos com esta aposta.
     * Implementado de forma diferente em cada subclasse (polimorfismo).
     */
    public abstract int calcularPontos();

    /** Retorna o tipo da aposta (identificação da subclasse). */
    public abstract String getTipoAposta();

    /** Retorna descrição detalhada do palpite. */
    public abstract String getDescricaoPalpite();

    @Override
    public String toString() {
        String partStr = partida.getMandante().getSigla()
                       + " vs "
                       + partida.getVisitante().getSigla();
        return "[" + getTipoAposta() + "] " + partStr
             + " | Palpite: " + getDescricaoPalpite()
             + " | Pontos: " + calcularPontos();
    }
}
