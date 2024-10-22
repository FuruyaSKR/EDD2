private Nodo rotacaoDireita(Nodo raiz){
    Nodo novaRaiz = raiz.esquerdo; //filho esquerdo se torna a nova raiz
    raiz.esquerdo = novaRaiz.direito;
    novaRaiz.direito = raiz;

    //atualizar alturas
    atualizarAlturas(raiz);
    atualizarAlturas(novaRaiz);
    return novaRaiz;
}

private Nodo rotacaoEsquerda(Nodo raiz){
    Nodo novaRaiz = raiz.direito; //filho direito se torna a nova raiz
    raiz.direito = novaRaiz.esquerdo;
    novaRaiz.esquerdo = raiz;

    //atualizar alturas
    atualizarAlturas(raiz);
    atualizarAlturas(novaRaiz);
    return novaRaiz;
}

private void atualizarAlturas(Nodo nodo){
    if(nodo != null){
        nodo.alturaEsq = (nodo.esquerdo == null) ? 0 :
        Math.max(nodo.esquerdo.alturaEsq, nodo.esquerdo.alturaDir) + 1;

        nodo.alturaDir = (nodo.direito == null) ? 0 :
        Math.max(nodo.direito.alturaEsq, nodo.direito.alturaDir) + 1;
    }
}

public void mostrar() {
    mostrar(raiz);
}

private void mostrar(Nodo raiz){
    if(raiz != null){
        mostrar(raiz.esquerdo);
        System.out.println(raiz.valor);
        mostrar(raiz.direito);
    }
}