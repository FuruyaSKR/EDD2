package TrabalhoHotelaria;

public class Cliente {
    private String id;
    private String cpf;
    private String nome;

    public Cliente(String id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "ID='" + id + '\'' +
                ", CPF='" + cpf + '\'' +
                ", Nome='" + nome + '\'' +
                '}';
    }

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

}
