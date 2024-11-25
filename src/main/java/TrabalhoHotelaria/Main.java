package TrabalhoHotelaria;

import java.util.List;

public class Main {
        public static void main(String[] args) {
                // Gerar quartos dinamicamente (2 andares, 5 quartos por andar)
                List<Quarto> listaQuartos = QuartoGenerator.gerarQuartos(2, 5); // 10 quartos

                // Criar árvores
                ArvoreRubroNegra arvorePrincipal = new ArvoreRubroNegra();
                ArvoreRubroNegra historicoReservas = new ArvoreRubroNegra();

                // Criar clientes
                Cliente cliente1 = new Cliente("123", "12345678900", "João Silva");
                Cliente cliente2 = new Cliente("456", "98765432100", "Maria Oliveira");
                Cliente cliente3 = new Cliente("789", "11111111111", "Carlos Pereira");
                Cliente cliente4 = new Cliente("101", "22222222222", "Ana Costa");
                Cliente cliente5 = new Cliente("202", "33333333333", "Pedro Lima");
                Cliente cliente6 = new Cliente("606", "44444444444", "Juliana Souza");
                Cliente cliente7 = new Cliente("707", "55555555555", "Fernando Almeida");
                Cliente cliente8 = new Cliente("808", "66666666666", "Laura Santos");

                // Inserir reservas
                arvorePrincipal.inserir(cliente1.getId(),
                                new Reserva(cliente1, listaQuartos.get(0), "2024-11-22", "2024-11-25")); // Quarto 101
                arvorePrincipal.inserir(cliente2.getCpf(),
                                new Reserva(cliente2, listaQuartos.get(1), "2024-11-22", "2024-11-24")); // Quarto 102
                arvorePrincipal.inserir(cliente3.getId(),
                                new Reserva(cliente3, listaQuartos.get(2), "2024-11-21", "2024-11-24")); // Quarto 103
                arvorePrincipal.inserir(cliente4.getId(),
                                new Reserva(cliente4, listaQuartos.get(3), "2024-11-22", "2024-11-26")); // Quarto 104
                arvorePrincipal.inserir(cliente5.getCpf(),
                                new Reserva(cliente5, listaQuartos.get(4), "2024-11-23", "2024-11-24")); // Quarto 105
                arvorePrincipal.inserir(cliente6.getId(),
                                new Reserva(cliente6, listaQuartos.get(5), "2024-11-22", "2024-11-25")); // Quarto 201
                arvorePrincipal.inserir(cliente7.getCpf(),
                                new Reserva(cliente7, listaQuartos.get(6), "2024-11-23", "2024-11-27")); // Quarto 202
                arvorePrincipal.inserir(cliente8.getId(),
                                new Reserva(cliente8, listaQuartos.get(7), "2024-11-21", "2024-11-24")); // Quarto 203

                // Exibir estado inicial da árvore principal
                System.out.println("====================");
                System.out.println("Árvore Principal Inicial");
                System.out.println("====================\n");
                arvorePrincipal.mostrarArvore();

                // Cálculo da taxa de ocupação antes do cancelamento
                double taxaOcupacaoInicial = arvorePrincipal.calcularTaxaOcupacao(listaQuartos, "2024-11-21",
                                "2024-11-26");
                System.out.println("\n====================");
                System.out.printf("Taxa de Ocupação: %.2f%%\n", taxaOcupacaoInicial);
                System.out.println("====================");

                // Alerta de ocupação
                if (taxaOcupacaoInicial >= 90.0) {
                        System.out.println("\n*** ALERTA: Capacidade do hotel atingiu ou ultrapassou 90%! ***");
                } else if (taxaOcupacaoInicial >= 80.0) {
                        System.out.println("\n*** AVISO: Capacidade do hotel está acima de 80%. ***");
                }

                // Consulta de disponibilidade com base em uma data
                arvorePrincipal.consultarDisponibilidadeQuartos(listaQuartos, "2024-11-24", "Luxo");

                // Cancelar uma reserva
                arvorePrincipal.cancelarReserva("123", historicoReservas);

                // Exibir árvore principal após cancelamento
                System.out.println("\n====================");
                System.out.println("Árvore Principal Após Cancelamento");
                System.out.println("====================\n");
                arvorePrincipal.mostrarArvore();

                // Mostrar histórico de reservas canceladas
                System.out.println("\n====================");
                System.out.println("Histórico de Reservas Canceladas");
                System.out.println("====================\n");
                historicoReservas.mostrarArvore();

                // Listar reservas por data de check-in
                arvorePrincipal.listarReservasPorDataCheckin();

                // Calcular taxa de ocupação após o cancelamento
                double taxaOcupacaoFinal = arvorePrincipal.calcularTaxaOcupacao(listaQuartos, "2024-11-21",
                                "2024-11-26");
                System.out.println("\n====================");
                System.out.printf("Taxa de Ocupação: %.2f%%\n", taxaOcupacaoFinal);
                System.out.println("====================\n");

                // Quartos mais e menos reservados
                arvorePrincipal.quartosMaisMenosReservados(listaQuartos);

                // Número de cancelamentos em um período
                int cancelamentos = arvorePrincipal.numeroCancelamentosNoPeriodo(historicoReservas, "2024-11-20",
                                "2024-11-26");
                System.out.println("\n====================");
                System.out.println("Número de Cancelamentos no Período");
                System.out.println("====================\n");
                System.out.println("Cancelamentos: " + cancelamentos);
                System.out.println("\n====================");
        }
}
