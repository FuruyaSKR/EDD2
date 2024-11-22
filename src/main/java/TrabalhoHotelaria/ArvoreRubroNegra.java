package TrabalhoHotelaria;

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
        while (nodo != raiz && nodo.pai.cor == Cor.VERMELHO) {
            pai = nodo.pai;
            avo = pai.pai;
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

    public boolean isQuartoDisponivel(String numeroQuarto, String dataCheckin, String dataCheckout) {
        return isQuartoDisponivelRecursivo(raiz, numeroQuarto, dataCheckin, dataCheckout);
    }

    private boolean isQuartoDisponivelRecursivo(Nodo nodo, String numeroQuarto, String dataCheckin,
            String dataCheckout) {
        if (nodo == null) {
            return true; // Se chegamos a um nó vazio, não há conflitos
        }

        Reserva reservaAtual = nodo.reserva;

        // Verificar se o número do quarto coincide e há conflito de datas
        if (reservaAtual.getQuarto().getNumero().equals(numeroQuarto)) {
            if (conflitoDeDatas(dataCheckin, dataCheckout, reservaAtual.getDataCheckin(),
                    reservaAtual.getDataCheckout())) {
                return false; // Quarto não está disponível
            }
        }

        // Verificar recursivamente na subárvore esquerda e direita
        return isQuartoDisponivelRecursivo(nodo.esquerdo, numeroQuarto, dataCheckin, dataCheckout) &&
                isQuartoDisponivelRecursivo(nodo.direito, numeroQuarto, dataCheckin, dataCheckout);
    }

    private boolean conflitoDeDatas(String dataCheckinNova, String dataCheckoutNova, String dataCheckinExistente,
            String dataCheckoutExistente) {
        // Um conflito ocorre se os períodos se sobrepõem
        return !(dataCheckoutNova.compareTo(dataCheckinExistente) <= 0
                || dataCheckinNova.compareTo(dataCheckoutExistente) >= 0);
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
}
