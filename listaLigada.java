package alocadordememória;

public class listaLigada {
    private No inicio;// endereço inicial da lista
    public int size;

    public listaLigada(){
        this.inicio = null; // lista esta vazia
    }
    
    public listaLigada(int size){
        this.inicio = null;
        this.size = size;
    }
    
    public void addInicio( int elemento ){
        this.inicio = new No(elemento,this.inicio);
    }
    
    public int remInicio(){
        //Antes de remover devemos verificar se temos pelo menos um Nó na lista,
        // não faz sentido remover algo que não existe, 
        if( !this.isEmpty()){ // se a lista nao esta vazia
            No ant = this.inicio; // guarda a celula que serah removida
            // avançar a referência que aponta para o primeiro  Nó (inicio) 
            // para o próximo Nó da lista
            this.inicio = this.inicio.getProx();
            return ant.getElemento();
        }
        else
            throw new RuntimeException("lista vazia!");
    }
    
    public boolean isEmpty(){
        return this.inicio == null;
    } 

    @Override
    public String toString() {
        return this.inicio + "";
    }
    
    // versao iterativa
    public boolean buscaID(int x) {
        for(No aux = this.inicio; aux != null; aux = aux.getProx()) {
            if( aux.getElemento() == x) {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean searchAndDestroyID(int id) {
        for(No aux = this.inicio; aux != null; aux = aux.getProx()) {
            if( aux.getElemento() == id) {
                return true; // Destroy
            }
        }
        
        return false;
    }
    
    // versao recursiva
    public boolean buscaRec(int x ){
        return busca(x, this.inicio);
    }
    
    private boolean busca(int target, No aux){
        // condicoes de parada
        if( aux == null )
            return false;
        
        if( aux.getElemento() == target)
            return true;
        
         return busca(target, aux.getProx());
    }

    public void addOrdenado(int newNo) {
        No aux = this.inicio;
        No ant = null;
        
        while( aux != null && aux.getElemento() < newNo ){
            ant = aux;
            aux = aux.getProx();
        }
        
        No novo = new No(newNo, aux);
        
        if( ant == null )
            this.inicio = novo;
        else
            ant.setProx(novo);
    }
    
    public void addFim(int x) {
        No aux = this.inicio;
        No ant = null;
        
        while( aux != null ){
            ant = aux;
            aux = aux.getProx();
        }
        
        No novo = new No(x, null);
        if( ant == null )// insere  no inicio
            this.inicio = novo;
        else
            ant.setProx(novo);
    }
    
    public boolean getAvailableSpace () {
        No aux = this.inicio;
        int usedSpace = 0;
        
        while( aux != null ){
            usedSpace += aux.getElemento();
            aux = aux.getProx();
        }
        
        if ( usedSpace < this.size) {
            return true;
        }
        return false;
    }
}

