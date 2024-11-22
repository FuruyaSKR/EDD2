package TrabalhoHotelaria;

public class Cliente {
    private String id; // ID único do cliente
    private String cpf; // CPF do cliente
    private String nome; // Nome do cliente

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Construtor
    public Cliente(String id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    // Método para exibir informações do cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "ID='" + id + '\'' +
                ", CPF='" + cpf + '\'' +
                ", Nome='" + nome + '\'' +
                '}';
    }
}
