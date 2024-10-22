package estrutura;

public class Main {
     public static void main(String[] args) {
       ArvoreBinaria arvore = new ArvoreBinaria();
       // Inserindo valores
       arvore.inserir(7);
       arvore.inserir(3);
       arvore.inserir(9);
       arvore.inserir(1);
       arvore.inserir(5);
       arvore.inserir(8);
       arvore.inserir(10);
       System.out.println("Árvore em ordem crescente:");
       arvore.mostrarEmOrdem();
      
       System.out.println("folhas da Árvore:");
       arvore.mostrarFolhas();
      
       System.out.println("nodos pares:");
       arvore.mostrarNodosPares();
      
       System.out.println("tamanho da arvore: "+ arvore.tamanhoArvore());
       // Removendo um nó
       arvore.remover(3);
       System.out.println("Árvore após remover 3:");
       arvore.mostrarEmOrdem();
       System.out.println("tamanho da arvore: "+ arvore.tamanhoArvore());
   }
}
