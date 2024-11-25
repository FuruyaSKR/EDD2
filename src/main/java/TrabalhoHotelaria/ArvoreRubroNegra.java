package TrabalhoHotelaria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArvoreRubroNegra {
    // Enum para definir as cores dos nós
    private enum Cor {
        VERMELHO,
        PRETO
    }

    private class Nodo {
        Reserva reserva;
        String chave; // ID ou CPF do cliente como chave
        Cor cor;
        Nodo esquerdo, direito, pai;

        public Nodo(String chave, Reserva reserva) {
            this.chave = chave;
            this.reserva = reserva;
            this.cor = Cor.VERMELHO; // Novos nós começam como vermelhos
            esquerdo = direito = pai = null;
        }
    }

    private Nodo raiz;

    public ArvoreRubroNegra() {
        raiz = null;
    }

    public void inserir(String chave, Reserva reserva) {
        Nodo novoNodo = new Nodo(chave, reserva);
        raiz = inserirNodo(raiz, novoNodo);
        corrigirInsercao(novoNodo);
    }

    private Nodo inserirNodo(Nodo atual, Nodo novoNodo) {
        if (atual == null) {
            return novoNodo;
        }
        if (novoNodo.chave.compareTo(atual.chave) < 0) {
            atual.esquerdo = inserirNodo(atual.esquerdo, novoNodo);
            atual.esquerdo.pai = atual;
        } else if (novoNodo.chave.compareTo(atual.chave) > 0) {
            atual.direito = inserirNodo(atual.direito, novoNodo);
            atual.direito.pai = atual;
        }
        return atual;
    }

    private void corrigirInsercao(Nodo nodo) {
        Nodo pai, avo;

        while (nodo != raiz && nodo.pai != null && nodo.pai.cor == Cor.VERMELHO) {
            pai = nodo.pai;
            avo = pai.pai;

            // Adicionado: evitar erro se o avô for nulo
            if (avo == null) {
                break;
            }

            if (pai == avo.esquerdo) {
                Nodo tio = avo.direito;
                if (tio != null && tio.cor == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    nodo = avo;
                } else {
                    if (nodo == pai.direito) {
                        nodo = pai;
                        rotacaoEsquerda(nodo);
                    }
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoDireita(avo);
                }
            } else {
                Nodo tio = avo.esquerdo;
                if (tio != null && tio.cor == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    nodo = avo;
                } else {
                    if (nodo == pai.esquerdo) {
                        nodo = pai;
                        rotacaoDireita(nodo);
                    }
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoEsquerda(avo);
                }
            }
        }
        raiz.cor = Cor.PRETO;
    }

    private void rotacaoEsquerda(Nodo nodo) {
        Nodo novoNodo = nodo.direito;
        nodo.direito = novoNodo.esquerdo;
        if (novoNodo.esquerdo != null) {
            novoNodo.esquerdo.pai = nodo;
        }
        novoNodo.pai = nodo.pai;
        if (nodo.pai == null) {
            raiz = novoNodo;
        } else if (nodo == nodo.pai.esquerdo) {
            nodo.pai.esquerdo = novoNodo;
        } else {
            nodo.pai.direito = novoNodo;
        }
        novoNodo.esquerdo = nodo;
        nodo.pai = novoNodo;
    }

    private void rotacaoDireita(Nodo nodo) {
        Nodo novoNodo = nodo.esquerdo;
        nodo.esquerdo = novoNodo.direito;
        if (novoNodo.direito != null) {
            novoNodo.direito.pai = nodo;
        }
        novoNodo.pai = nodo.pai;
        if (nodo.pai == null) {
            raiz = novoNodo;
        } else if (nodo == nodo.pai.direito) {
            nodo.pai.direito = novoNodo;
        } else {
            nodo.pai.esquerdo = novoNodo;
        }
        novoNodo.direito = nodo;
        nodo.pai = novoNodo;
    }

    // Método para exibir a árvore
    public void mostrarArvore() {
        if (raiz == null) {
            System.out.println("A árvore está vazia!");
        } else {
            mostrarArvoreRecursiva(raiz, "", true);
        }
    }

    private void mostrarArvoreRecursiva(Nodo nodo, String prefixo, boolean isFilhoDireito) {
        if (nodo != null) {
            System.out.println(prefixo + (isFilhoDireito ? "|---" : "|---") + nodo.chave + " (" + nodo.cor + ")");
            String novoPrefixo = prefixo + (isFilhoDireito ? " " : "|");
            mostrarArvoreRecursiva(nodo.direito, novoPrefixo, true);
            mostrarArvoreRecursiva(nodo.esquerdo, novoPrefixo, false);
        }
    }

    private Nodo removerNodo(Nodo atual, String chave) {
        if (atual == null) {
            return null;
        }

        if (chave.compareTo(atual.chave) < 0) {
            atual.esquerdo = removerNodo(atual.esquerdo, chave);
        } else if (chave.compareTo(atual.chave) > 0) {
            atual.direito = removerNodo(atual.direito, chave);
        } else {
            // Nodo encontrado, executar a remoção
            if (atual.esquerdo == null) {
                return atual.direito;
            } else if (atual.direito == null) {
                return atual.esquerdo;
            } else {
                Nodo substituto = encontrarMenor(atual.direito);
                atual.chave = substituto.chave;
                atual.reserva = substituto.reserva;
                atual.direito = removerNodo(atual.direito, substituto.chave);
            }
        }
        return atual;
    }

    private Nodo encontrarMenor(Nodo nodo) {
        while (nodo.esquerdo != null) {
            nodo = nodo.esquerdo;
        }
        return nodo;
    }

    // Método de busca por chave (ID ou CPF)
    public Reserva buscar(String chave) {
        Nodo resultado = buscarNodo(raiz, chave);
        return (resultado != null) ? resultado.reserva : null;
    }

    private Nodo buscarNodo(Nodo atual, String chave) {
        if (atual == null || chave.equals(atual.chave)) {
            return atual;
        }
        if (chave.compareTo(atual.chave) < 0) {
            return buscarNodo(atual.esquerdo, chave);
        } else {
            return buscarNodo(atual.direito, chave);
        }
    }

    // Método para verificar disponibilidade em um intervalo de datas
    public boolean isQuartoDisponivelIntervalo(String numeroQuarto, String dataCheckin, String dataCheckout) {
        return isQuartoDisponivelIntervaloRecursivo(raiz, numeroQuarto, dataCheckin, dataCheckout);
    }

    private boolean isQuartoDisponivelIntervaloRecursivo(Nodo nodo, String numeroQuarto, String dataCheckin,
            String dataCheckout) {
        if (nodo == null) {
            return true;
        }

        Reserva reservaAtual = nodo.reserva;

        if (reservaAtual.getQuarto().getNumero().equals(numeroQuarto)) {
            if (conflitoDeDatas(dataCheckin, dataCheckout, reservaAtual.getDataCheckin(),
                    reservaAtual.getDataCheckout())) {
                System.out.printf("Quarto %s está ocupado entre %s e %s (reservado: %s - %s)%n",
                        numeroQuarto, dataCheckin, dataCheckout, reservaAtual.getDataCheckin(),
                        reservaAtual.getDataCheckout());
                return false; // Quarto Ocupado
            }
        }

        return isQuartoDisponivelIntervaloRecursivo(nodo.esquerdo, numeroQuarto, dataCheckin, dataCheckout) &&
                isQuartoDisponivelIntervaloRecursivo(nodo.direito, numeroQuarto, dataCheckin, dataCheckout);
    }

    // Método para verificar disponibilidade em uma data específica
    public boolean isQuartoDisponivel(String numeroQuarto, String dataConsulta) {
        return isQuartoDisponivelRecursivo(raiz, numeroQuarto, dataConsulta);
    }

    private boolean isQuartoDisponivelRecursivo(Nodo nodo, String numeroQuarto, String dataConsulta) {
        if (nodo == null) {
            return true; // Se chegamos a um nó vazio, o quarto está disponível
        }

        Reserva reservaAtual = nodo.reserva;

        // Verificar se o número do quarto coincide e a data de consulta está dentro do
        // intervalo
        if (reservaAtual.getQuarto().getNumero().equals(numeroQuarto)) {
            if (dataConsulta.compareTo(reservaAtual.getDataCheckin()) >= 0 &&
                    dataConsulta.compareTo(reservaAtual.getDataCheckout()) <= 0) {
                return false; // Quarto está ocupado
            }
        }

        // Verificar recursivamente na subárvore esquerda e direita
        return isQuartoDisponivelRecursivo(nodo.esquerdo, numeroQuarto, dataConsulta) &&
                isQuartoDisponivelRecursivo(nodo.direito, numeroQuarto, dataConsulta);
    }

    private boolean conflitoDeDatas(String dataCheckinNova, String dataCheckoutNova, String dataCheckinExistente,
            String dataCheckoutExistente) {
        // Um conflito ocorre se os períodos se sobrepõem
        return !(dataCheckoutNova.compareTo(dataCheckinExistente) <= 0
                || dataCheckinNova.compareTo(dataCheckoutExistente) >= 0);
    }

    public boolean cancelarReserva(String chave, ArvoreRubroNegra historicoReservas) {
        System.out.println("\n====================");
        System.out.println("Cancelando Reserva: Cliente '" + chave + "'");
        System.out.println("====================\n");

        Nodo nodoParaRemover = buscarNodo(raiz, chave);
        if (nodoParaRemover == null) {
            return false;
        }

        System.out.println("Cancelando a seguinte reserva:");
        System.out.println(nodoParaRemover.reserva + "\n");

        // Adicionar ao histórico de reservas canceladas
        historicoReservas.inserir(nodoParaRemover.chave, nodoParaRemover.reserva);

        // Remover da árvore principal
        raiz = removerNodo(raiz, chave);

        System.out.println("\nEstado Atual do Histórico de Reservas Canceladas:");
        historicoReservas.mostrarArvore();

        System.out.println("\n====================\n");
        return true;
    }

    // Exibir histórico de reservas canceladas
    public void mostrarHistorico(ArvoreRubroNegra historicoReservas) {
        System.out.println("Histórico de Reservas Canceladas:");
        historicoReservas.mostrarArvore();
    }

    public void consultarReservaPorCliente(String chave) {
        Reserva reserva = buscar(chave);
        if (reserva != null) {
            System.out.println("Reserva encontrada:");
            System.out.println(reserva);
        } else {
            System.out.println("Nenhuma reserva encontrada para a chave: " + chave);
        }
    }

    public void consultarDisponibilidadeQuartos(List<Quarto> listaQuartos, String dataConsulta, String categoria) {
        System.out.println("\n====================");
        System.out.println("Consulta de Disponibilidade de Quartos\n");
        System.out.println("Categoria: " + categoria + " | Data: " + dataConsulta);
        System.out.println("====================\n");

        for (Quarto quarto : listaQuartos) {
            boolean disponivel = isQuartoDisponivel(quarto.getNumero(), dataConsulta);
            if (quarto.getCategoria().equalsIgnoreCase(categoria)) {
                if (disponivel) {
                    System.out.println("[DISPONÍVEL] " + quarto);
                } else {
                    System.out.println("[OCUPADO] " + quarto.getNumero());
                }
            }
        }

        System.out.println("\n====================\n");
    }

    // Listar reservas por data de check-in
    public void listarReservasPorDataCheckin() {
        List<Reserva> reservas = new ArrayList<>();
        coletarReservasEmOrdem(raiz, reservas);

        // Ordenar reservas por data de check-in
        Collections.sort(reservas, Comparator.comparing(Reserva::getDataCheckin));

        System.out.println("\n====================");
        System.out.println("Listagem de Reservas por Data de Check-in");
        System.out.println("====================\n");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
        System.out.println("\n====================\n");
    }

    // Método recursivo para coletar todas as reservas em ordem
    private void coletarReservasEmOrdem(Nodo nodo, List<Reserva> reservas) {
        if (nodo != null) {
            coletarReservasEmOrdem(nodo.esquerdo, reservas); // Visitar subárvore esquerda
            reservas.add(nodo.reserva); // Adicionar reserva atual
            coletarReservasEmOrdem(nodo.direito, reservas); // Visitar subárvore direita
        }
    }

    public double calcularTaxaOcupacao(List<Quarto> listaQuartos, String dataInicio, String dataFim) {
        int totalQuartos = listaQuartos.size(); // Verifica o total de quartos
        int quartosOcupados = 0; // Contador para os quartos ocupados

        if (totalQuartos == 0) {
            return 0.0;
        }

        // Calcula a taxa de ocupação
        double taxa = ((double) quartosOcupados / totalQuartos) * 100.0;
        return taxa;
    }

    public void quartosMaisMenosReservados(List<Quarto> listaQuartos) {
        // Mapa para armazenar o número de reservas por quarto
        Map<String, Integer> reservasPorQuarto = new HashMap<>();

        // Inicializar o mapa com os quartos
        for (Quarto quarto : listaQuartos) {
            reservasPorQuarto.put(quarto.getNumero(), 0);
        }

        // Contar reservas por quarto
        contarReservasPorQuarto(raiz, reservasPorQuarto);

        // Encontrar os quartos mais e menos reservados
        String maisReservado = null, menosReservado = null;
        int maxReservas = Integer.MIN_VALUE, minReservas = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : reservasPorQuarto.entrySet()) {
            int reservas = entry.getValue();
            String numeroQuarto = entry.getKey();

            if (reservas > maxReservas) {
                maxReservas = reservas;
                maisReservado = numeroQuarto;
            }
            if (reservas < minReservas) {
                minReservas = reservas;
                menosReservado = numeroQuarto;
            }
        }

        // Exibir resultados
        System.out.println("\n====================");
        System.out.println("Relatório de Quartos Mais e Menos Reservados");
        System.out.println("====================\n");
        System.out.println("Quarto Mais Reservado: " + maisReservado + " (Reservas: " + maxReservas + ")");
        System.out.println("Quarto Menos Reservado: " + menosReservado + " (Reservas: " + minReservas + ")");
        System.out.println("\n====================\n");
    }

    // Método recursivo para contar reservas por quarto
    private void contarReservasPorQuarto(Nodo nodo, Map<String, Integer> reservasPorQuarto) {
        if (nodo != null) {
            contarReservasPorQuarto(nodo.esquerdo, reservasPorQuarto);

            String numeroQuarto = nodo.reserva.getQuarto().getNumero();
            reservasPorQuarto.put(numeroQuarto, reservasPorQuarto.get(numeroQuarto) + 1);

            contarReservasPorQuarto(nodo.direito, reservasPorQuarto);
        }
    }

    public int numeroCancelamentosNoPeriodo(ArvoreRubroNegra historicoReservas, String dataInicio, String dataFim) {
        List<Reserva> reservasCanceladas = new ArrayList<>();
        coletarReservasEmOrdem(historicoReservas.raiz, reservasCanceladas);

        int cancelamentos = 0;

        for (Reserva reserva : reservasCanceladas) {
            if (conflitoDeDatas(dataInicio, dataFim, reserva.getDataCheckin(), reserva.getDataCheckout())) {
                cancelamentos++;
            }
        }

        return cancelamentos;
    }

}
