package memorysimulator;

public class listaLigada {
    private No inicio;// endereço inicial da lista
    public int size = 0;
    
    // Para exeibição dos nós
    @Override
    public String toString() {
        return this.inicio + "";
    }
    
    // Construtor para a lista alocada
    public listaLigada(){
        this.inicio = null;
    }
    
    // Construtor para a lista livre
    public listaLigada(int size){
        this.size = size;
        
        No novo = new No(0, size, 0, null);
        this.inicio = novo;
    }
    
    // Verifica se esta vazia
    public boolean isEmpty(){
        return this.inicio == null;
    }
    
    // Verifica se esta cheia
    public boolean isFull(){
        No aux = this.inicio;
        No ant = null;
        int soma = 0;
        
        while( aux != null && soma <= this.size ){
            soma += aux.getEndereco();
            ant = aux;
            aux = aux.getProx();
            
            if(soma >= this.size) {
                return true;
            }
        }
        
        return false;
    }

    // Adiciona No a lista
    public void add(int noID, int tamanho, listaLigada listaLivre) {
        No novo;
        No aux = this.inicio;
        No atual = null;
        
        while( aux != null ){
            atual = aux; // Salva o No anterior
            aux = aux.getProx(); // Pega o próximo No
        }
        
        if (atual == null) {
            novo = new No(noID, tamanho, 0, aux);
        } else {
            novo = new No(noID, tamanho, atual.getEndereco() + atual.getTamanho(), aux);
        }
        
        if( atual == null ) {
            this.inicio = novo;
        } else
            atual.setProx(novo);
    }
    
    public void addOrdenado(No newNo) {
        No aux = this.inicio;
        No ant = null;
        
        while( aux != null && aux.getEndereco() < newNo.getEndereco() ){
            ant = aux;
            aux = aux.getProx();
        }
        
        No novo = new No(noID, tamanho, 0, aux);
        
        if( ant == null )
            this.inicio = novo;
        else
            ant.setProx(novo);
    }
    
    public boolean getAvailableSpace () {
        No aux = this.inicio;
        int usedSpace = 0;
        
        while( aux != null ){
            usedSpace += aux.getEndereco();
            aux = aux.getProx();
        }
        
        if ( usedSpace < this.size) {
            return true;
        }
        
        return false;
    }
    
    public No getInicio() {
        return inicio;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // versao iterativa
    public boolean buscaID(int x) {
        for(No aux = this.inicio; aux != null; aux = aux.getProx()) {
            if( aux.getEndereco() == x) {
                return true;
            }
        }
        
        return false;
    }
    
    public void searchAndDestroyID(int id) {
        for(No aux = this.inicio; aux != null; aux = aux.getProx()) {
            if( aux.getEndereco() == id) {
                aux = null; // Destroy
            }
        }
    }
    
    // versao recursiva
    public boolean buscaRec(int x ){
        return busca(x, this.inicio);
    }
    
    private boolean busca(int target, No aux){
        // condicoes de parada
        if( aux == null )
            return false;
        
        if( aux.getID() == target)
            return true;
        
         return busca(target, aux.getProx());
    }
}
