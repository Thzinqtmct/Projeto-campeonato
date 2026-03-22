package model;

// administrador do sistema, herda de Pessoa
public class Administrador extends Pessoa {

    // Construtor padrão
    public Administrador() {
        super();
    }

    // Construtor sobrecarregado — chama o construtor da superclasse
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // Sobreposição (override) de métodos abstratos da superclasse Pessoa
    @Override
    public String getTipo() {
        return "Administrador";
    }

    @Override
    public String getInfo() {
        return "=== Administrador ===\n"
             + "Nome : " + getNome() + "\n"
             + "Email: " + getEmail();
    }
}
