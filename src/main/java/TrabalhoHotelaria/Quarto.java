package TrabalhoHotelaria;

public class Quarto {
    private String numero;
    private String categoria;

    public Quarto(String numero, String categoria) {
        this.numero = numero;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "Número='" + numero + '\'' +
                ", Categoria='" + categoria + '\'' +
                '}';
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNumero() {
        return numero;
    }

    public String getCategoria() {
        return categoria;
    }

}
