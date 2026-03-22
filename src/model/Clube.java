package model;

/**
 * Representa um clube de futebol.
 * Demonstra: encapsulamento, construtor padrão e sobrecarregado.
 */
public class Clube {
    private String nome;
    private String sigla;

    // Construtor padrão
    public Clube() {
        this.nome = "";
        this.sigla = "";
    }

    // Construtor sobrecarregado
    public Clube(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla.toUpperCase();
    }

    // Getters e Setters (encapsulamento)
    public String getNome() { return nome; }
    public String getSigla() { return sigla; }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        if (sigla == null || sigla.isBlank()) throw new IllegalArgumentException("Sigla inválida.");
        this.sigla = sigla.toUpperCase();
    }

    @Override
    public String toString() {
        return nome + " (" + sigla + ")";
    }
}
