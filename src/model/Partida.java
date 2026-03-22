package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma partida entre dois clubes.
 * Demonstra: encapsulamento, construtor padrão e sobrecarregado.
 */
public class Partida {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final int MINUTOS_LIMITE_APOSTA = 20;

    private Clube mandante;
    private Clube visitante;
    private LocalDateTime dataHora;
    private Placar resultado;
    private boolean encerrada;

    // Construtor padrão
    public Partida() {
        this.encerrada = false;
        this.resultado = null;
    }

    // Construtor sobrecarregado
    public Partida(Clube mandante, Clube visitante, LocalDateTime dataHora) {
        this();
        this.mandante = mandante;
        this.visitante = visitante;
        this.dataHora = dataHora;
    }

    // Getters e Setters (encapsulamento)
    public Clube getMandante()        { return mandante; }
    public Clube getVisitante()       { return visitante; }
    public LocalDateTime getDataHora(){ return dataHora; }
    public Placar getResultado()      { return resultado; }
    public boolean isEncerrada()      { return encerrada; }

    public void setMandante(Clube mandante)   { this.mandante = mandante; }
    public void setVisitante(Clube visitante) { this.visitante = visitante; }
    public void setDataHora(LocalDateTime dt) { this.dataHora = dt; }

    /** Registra o resultado real e encerra a partida. */
    public void registrarResultado(Placar resultado) {
        this.resultado = resultado;
        this.encerrada = true;
    }

    /**
     * Verifica se ainda é possível apostar nesta partida
     * (somente até 20 minutos antes do início).
     */
    public boolean apostasAbertas() {
        return LocalDateTime.now().isBefore(dataHora.minusMinutes(MINUTOS_LIMITE_APOSTA));
    }

    public String getDataHoraFormatada() {
        return dataHora != null ? dataHora.format(FORMATTER) : "N/A";
    }

    @Override
    public String toString() {
        String status;
        if (encerrada) {
            status = "[ENCERRADA] Placar: " + resultado;
        } else if (!apostasAbertas()) {
            status = "[EM ANDAMENTO / APOSTAS FECHADAS]";
        } else {
            status = "[AGENDADA]";
        }
        return mandante + " vs " + visitante + " | " + getDataHoraFormatada() + " | " + status;
    }
}
