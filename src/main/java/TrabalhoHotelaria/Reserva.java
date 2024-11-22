package TrabalhoHotelaria;

public class Reserva {
    private Cliente cliente;
    private Quarto quarto;
    private String dataCheckin;
    private String dataCheckout;

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quarto getQuarto() {
        return this.quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public String getDataCheckin() {
        return this.dataCheckin;
    }

    public void setDataCheckin(String dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public String getDataCheckout() {
        return this.dataCheckout;
    }

    public void setDataCheckout(String dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Reserva(Cliente cliente, Quarto quarto, String dataCheckin, String dataCheckout) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "Cliente=" + cliente +
                ", Quarto=" + quarto +
                ", DataCheckin='" + dataCheckin + '\'' +
                ", DataCheckout='" + dataCheckout + '\'' +
                '}';
    }
}