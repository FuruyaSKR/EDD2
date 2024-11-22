package TrabalhoHotelaria;

public class Main {
    public static void main(String[] args) {
        // Criar árvore Rubro-Negra
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();

        // Criar clientes
        Cliente cliente1 = new Cliente("123", "12345678900", "João Silva");
        Cliente cliente2 = new Cliente("456", "98765432100", "Maria Oliveira");

        // Criar quartos
        Quarto quarto1 = new Quarto("101", "Luxo");
        Quarto quarto2 = new Quarto("202", "Econômico");

        // Criar reservas
        Reserva reserva1 = new Reserva(cliente1, quarto1, "2024-11-22", "2024-11-25");
        Reserva reserva2 = new Reserva(cliente2, quarto2, "2024-11-23", "2024-11-26");

        // Inserir reservas na árvore
        if (arvore.isQuartoDisponivel("101", "2024-11-22", "2024-11-25")) {
            arvore.inserir(cliente1.getId(), reserva1);
            System.out.println("Reserva do cliente " + cliente1.getNome() + " inserida com sucesso!");
        } else {
            System.out.println("Quarto 101 não está disponível para o período de 2024-11-22 a 2024-11-25.");
        }

        if (arvore.isQuartoDisponivel("202", "2024-11-23", "2024-11-26")) {
            arvore.inserir(cliente2.getCpf(), reserva2);
            System.out.println("Reserva do cliente " + cliente2.getNome() + " inserida com sucesso!");
        } else {
            System.out.println("Quarto 202 não está disponível para o período de 2024-11-23 a 2024-11-26.");
        }

        // Mostrar árvore completa
        System.out.println("\nÁrvore Rubro-Negra:");
        arvore.mostrarArvore();

        // Buscar reserva por ID
        System.out.println("\nBuscando por ID '123':");
        Reserva resultadoID = arvore.buscar("123");
        System.out.println(resultadoID != null ? resultadoID : "Reserva não encontrada!");

        // Buscar reserva por CPF
        System.out.println("\nBuscando por CPF '98765432100':");
        Reserva resultadoCPF = arvore.buscar("98765432100");
        System.out.println(resultadoCPF != null ? resultadoCPF : "Reserva não encontrada!");

        // Testar disponibilidade para um período conflitante
        System.out.println("\nVerificando disponibilidade para o Quarto 101 no período 2024-11-24 a 2024-11-26:");
        boolean disponibilidade = arvore.isQuartoDisponivel("101", "2024-11-24", "2024-11-26");
        System.out.println(disponibilidade ? "Quarto disponível!" : "Quarto não disponível!");
    }
}
