package ArvoreAVL;

public class ArvoreRubroNegra {
    // Enum para definir as cores dos nós
    private enum Cor {
        VERMELHO,
        PRETO
    }

    private class Nodo {
        int valor;
        Cor cor;
        Nodo esquerdo, direito, pai;

        // Construtor do Nodo
        public Nodo(int valor) {
            this.valor = valor;
            this.cor = Cor.VERMELHO; // Novos nós são inserirdos como vermelhos
            esquerdo = direito = pai = null;
        }
    }

    private Nodo raiz;

    // Construtor da árvore
    public ArvoreRubroNegra() {
        raiz = null;
    }

    public void Inserir(int valor) {
        Nodo novoNodo = new Nodo(valor);
        raiz = inserirNodo(raiz, novoNodo);
        corrigirInsercao(novoNodo); // Corrigi eventuais violações das propriedades da árvore rubro-negra
    }

    private Nodo inserirNodo(Nodo atual, Nodo novoNodo) {
        // Caso base: se a posição for nula, insere o novo nó
        if (atual == null) {
            return novoNodo;
        }
        // Se o valor do nó for menor, insere à esquerda
        if (novoNodo.valor < atual.valor) {
            atual.esquerdo = inserirNodo(atual.esquerdo, novoNodo);
            atual.esquerdo.pai = atual;

            // Se o valor do novo nó for maior, insere à direita
        } else if (novoNodo.valor > atual.valor) {
            atual.direito = inserirNodo(atual.direito, novoNodo);
            atual.direito.pai = atual;
        }
        return atual;
    }

    private void corrigirInsercao(Nodo nodo) {
        Nodo pai, avo;

        // Enquanto o pai do nó for vermelho(e, portanto houver violação)
        while (nodo != raiz && nodo.pai.cor == Cor.VERMELHO) {
            pai = nodo.pai;
            avo = pai.pai;

            // Caso 1: Pai do nó e filho esquerdo do avô
            if (pai == avo.esquerdo) {
                Nodo tio = avo.direito; // Tio do nó
                // Caso 1.1: O tio é vermelho(necessário recolorir)
                if (tio != null && tio.cor == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO; // Pai fica preto
                    tio.cor = Cor.PRETO; // Tio fica preto
                    avo.cor = Cor.VERMELHO; // Avô fica vermelho
                    nodo = avo; // continua verificando o avô
                }
                // Caso 1.2: O tio é preto ou nulo(necessário fazer rotações)
                else {
                    // Caso 1.2.1: O nó é filho direito(rotação à esquerda)
                    if (nodo == pai.direito) {
                        nodo = pai;
                        rotacaoEsquerda(nodo);
                    }
                    // Caso 1.2.2: O nó é filho esquerdo(rotação à direita)
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoDireita(avo);
                }
                // Caso 2: Pai do nó é filho direito do avô
            } else {
                Nodo tio = avo.esquerdo;
                // Caso 2.1: O tio é vermelho (necessário recolorir)
                if (tio != null && tio.cor == Cor.VERMELHO) {
                    pai.cor = Cor.PRETO;
                    tio.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    nodo = avo;
                }
                // Caso 2.2: O tio é preto ou nulo(necessário fazer rotações)
                else {
                    // Caso 2.2.1: O nó é filho esquerdo(rotação à direita)
                    if (nodo == pai.esquerdo) {
                        nodo = pai;
                        rotacaoDireita(nodo);
                    }
                    // Caso 2.2.2: O nó é filho direito(ortação à esquerda)
                    pai.cor = Cor.PRETO;
                    avo.cor = Cor.VERMELHO;
                    rotacaoEsquerda(avo);
                }
            }
        }
        raiz.cor = Cor.PRETO;
    }

    // Método para realizar uma rotação à esquerda
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

    // Método para realizar uma rotação à direita
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

    // Método auxiliar para exibir a árvore
    public void mostrarArvore() {
        if (raiz == null) {
            System.out.println("A árvore está vazia!");
        } else {
            mostrarArvoreRecursiva(raiz, "", true);
        }
    }

    private void mostrarArvoreRecursiva(Nodo nodo, String prefixo, boolean isFilhoDireito) {
        if (nodo != null) {
            System.out.println(prefixo + (isFilhoDireito ? "|---" : "|---") + nodo.valor + " (" + nodo.cor + ")");
            String novoPrefixo = prefixo + (isFilhoDireito ? " " : "|");
            mostrarArvoreRecursiva(nodo.direito, novoPrefixo, true);
            mostrarArvoreRecursiva(nodo.esquerdo, novoPrefixo, false);
        }
    }
}