package model;

/**
 * Enum que representa o resultado de uma partida.
 * Demonstra encapsulamento com atributo privado e getter.
 */
public enum Resultado {
    MANDANTE("Mandante vence"),
    EMPATE("Empate"),
    VISITANTE("Visitante vence");

    private final String descricao;

    Resultado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
