public class ArvoreBinaria {
   private Nodo raiz;
   int tamanho;
   
   // Método para inserir um novo nó
   public void inserir(int chave) {
       raiz = inserirNodo(raiz, chave);
   }
  
   private Nodo inserirNodo(Nodo nodo, int chave) {
       if (nodo == null) {
           return new Nodo(chave); // Cria um novo nó se o lugar estiver vazio
       }
       if (chave < nodo.chave) {
           nodo.esq = inserirNodo(nodo.esq, chave); // Insere à esquerda
       } else if (chave > nodo.chave) {
           nodo.dir = inserirNodo(nodo.dir, chave); // Insere à direita
       }
       return nodo; // Retorna o nodo (não alterado)
   }
   // Método para mostrar a árvore em ordem crescente
   public void mostrarEmOrdem() {
       mostrarEmOrdem(raiz);
       System.out.println(); // Para pular uma linha após a impressão
   }
  
   private void mostrarEmOrdem(Nodo nodo) {
       if (nodo != null) {
           mostrarEmOrdem(nodo.esq); // Visita a subárvore esquerda
           System.out.print(nodo.chave + " "); // Mostra a chave do nó
           mostrarEmOrdem(nodo.dir); // Visita a subárvore direita
       }
   }
   public void remover(int chave) {
       raiz = removerNodo(raiz, chave);
   }
  
   private Nodo removerNodo(Nodo raiz, int chave) {
       if (raiz == null) {
           return null; // Se o nó é nulo, não faz nada
       }
      
       if (chave < raiz.chave) {
           raiz.esq = removerNodo(raiz.esq, chave); // Chave à esquerda
       } else if (chave > raiz.chave) {
           raiz.dir = removerNodo(raiz.dir, chave); // Chave à direita
       } else {
           // Encontramos o nó a ser removido
           if (raiz.esq == null) {
               return raiz.dir; // Caso onde o nó não possui filho à esquerda
           } else if (raiz.dir == null) {
               return raiz.esq; // Caso onde o nó não possui filho à direita
           } else {
               // Caso onde o nó possui dois filhos
               Nodo sucessor = encontrarSucessor(raiz.dir);
               raiz.chave = sucessor.chave; // Substitui pela chave do sucessor
               raiz.dir = removerNodo(raiz.dir, sucessor.chave); // Remove o sucessor
           }
       }
       return raiz;
   }
  
   private Nodo encontrarSucessor(Nodo nodo) {
       while (nodo.esq != null) {
           nodo = nodo.esq; // Encontra o menor valor na subárvore direita
       }
       return nodo;
   }
  
   public int maiorChave(Nodo nodo) {
   	while(nodo.dir != null) {
   		nodo = nodo.dir;
   	}
   	return nodo.chave;
   }
  
   public void mostrarFolhas() {
   	mostrarFolhas(raiz);
       System.out.println(); // Para pular uma linha após a impressão
   }
  
   private void mostrarFolhas(Nodo nodo) {
       if (nodo != null) {
       	mostrarFolhas(nodo.esq); // Visita a subárvore esquerda
       	mostrarFolhas(nodo.dir); // Visita a subárvore direita
          
       	if (nodo.esq == null && nodo.dir == null) {
       		System.out.println(nodo.chave + " ");
       	}
       }
   }
  
   public void mostrarNodosPares() {
   	mostrarNodosPares(raiz);
       System.out.println(); // Para pular uma linha após a impressão
   }
  
   private void mostrarNodosPares(Nodo nodo) {
       if (nodo != null) {
       	mostrarNodosPares(nodo.esq); // Visita a subárvore esquerda
       	mostrarNodosPares(nodo.dir); // Visita a subárvore direita
          
       	if (nodo.chave % 2 == 0) {
       		System.out.println(nodo.chave + " ");
       	}
       }
   }
  
   public int tamanhoArvore() {
   	tamanho = 0;
   	tamanhoArvore(raiz);
       System.out.println(); // Para pular uma linha após a impressão
       return tamanho;
   }
  
   private void tamanhoArvore(Nodo nodo) {
       if (nodo != null) {
       	tamanhoArvore(nodo.esq);
       	tamanhoArvore(nodo.dir);
       	tamanho +=1;
       }
   }
