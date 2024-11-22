package ArvoreAVL;

public class Main {
    public static void main(String[] args) {
        // Instanciando a árvore rubro-negra
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();

        // Inserindo valores na árvore
        System.out.println("Inserindo valores na árvore...");
        int[] valores = { 10, 20, 30, 15, 25, 5, 1, 7 };
        for (int valor : valores) {
            System.out.println("Inserindo: " + valor);
            arvore.Inserir(valor);
        }

        // Mostrando a estrutura da árvore
        System.out.println("\nEstrutura da árvore após inserções:");
        arvore.mostrarArvore();

        // Teste adicional: verificar comportamento após inserir um valor repetido
        System.out.println("\nTentando inserir valor duplicado (20):");
        arvore.Inserir(20);
        arvore.mostrarArvore();

        // Teste adicional: árvore vazia
        System.out.println("\nTestando uma nova árvore vazia:");
        ArvoreRubroNegra arvoreVazia = new ArvoreRubroNegra();
        arvoreVazia.mostrarArvore();
    }
}
