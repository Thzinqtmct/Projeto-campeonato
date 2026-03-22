package model;

// classe base para Administrador e Participante
public abstract class Pessoa {
    private String nome;
    private String email;
    private String senha;

    // Construtor padrão
    public Pessoa() {
        this.nome  = "";
        this.email = "";
        this.senha = "";
    }

    // Construtor sobrecarregado
    public Pessoa(String nome, String email, String senha) {
        this.nome  = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters (encapsulamento — senha não tem getter público)
    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email inválido.");
        this.email = email;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isBlank()) throw new IllegalArgumentException("Senha inválida.");
        this.senha = senha;
    }

    /** Verifica as credenciais de login. */
    public boolean autenticar(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    // Métodos abstratos — polimorfismo por sobreposição nas subclasses
    public abstract String getTipo();
    public abstract String getInfo();

    @Override
    public String toString() {
        return "[" + getTipo() + "] " + nome + " <" + email + ">";
    }
}
