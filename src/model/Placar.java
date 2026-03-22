package model;

/**
 * Representa o placar de uma partida.
 * Demonstra: encapsulamento, construtor padrão e sobrecarregado.
 */
public class Placar {
    private int golsMandante;
    private int golsVisitante;

    // Construtor padrão
    public Placar() {
        this.golsMandante = 0;
        this.golsVisitante = 0;
    }

    // Construtor sobrecarregado
    public Placar(int golsMandante, int golsVisitante) {
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
    }

    // Getters e Setters (encapsulamento)
    public int getGolsMandante() { return golsMandante; }
    public int getGolsVisitante() { return golsVisitante; }

    public void setGolsMandante(int golsMandante) {
        if (golsMandante < 0) throw new IllegalArgumentException("Gols não pode ser negativo.");
        this.golsMandante = golsMandante;
    }

    public void setGolsVisitante(int golsVisitante) {
        if (golsVisitante < 0) throw new IllegalArgumentException("Gols não pode ser negativo.");
        this.golsVisitante = golsVisitante;
    }

    public Resultado getResultado() {
        if (golsMandante > golsVisitante) return Resultado.MANDANTE;
        if (golsMandante == golsVisitante) return Resultado.EMPATE;
        return Resultado.VISITANTE;
    }

    public boolean equals(Placar outro) {
        if (outro == null) return false;
        return this.golsMandante == outro.golsMandante
            && this.golsVisitante == outro.golsVisitante;
    }

    @Override
    public String toString() {
        return golsMandante + " x " + golsVisitante;
    }
}
