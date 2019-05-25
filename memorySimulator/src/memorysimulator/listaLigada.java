package memorysimulator;

public class listaLigada {
    private No inicio;// endereço inicial da lista    
    
    // Construtor para a LISTA LIVRE
    public listaLigada(int size){
        // CONSTRUTOR: No(int tamanho, No prox, int endereco)
        No novo = new No(size, 0);
        this.inicio = novo;
    }
    
    // Construtor para a LISTA ALOCADA
    public listaLigada(){
        // CONSTRUTOR: No(int ID, int tamanho, No prox, int endereco)
        this.inicio = null;
    }
    
    public No getInicio() {
        return inicio;
    }
    
    // Para exeibição dos nós
    @Override
    public String toString() {
        return this.inicio + "";
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
        
        while( aux != null && soma <= aux.getTamanho() ){
            soma += aux.getEndereco();
            ant = aux;
            aux = aux.getProx();
            
            if(soma >= aux.getTamanho()) {
                return true;
            }
        }
        
        return false;
    }
    
    
    // Adiciona No a LISTA LIVRE, chamada somente quando um processo é finalizado
    // para separar os Nos de memória

    
    // Adiciona No a LISTA ALOCADA
    public listaLigada addAlocada(int noID, int tamanho, listaLigada listaLivre) {
        // LISTA LIVRE é recebida como parametro
        No aux = this.inicio;
        No atual = null;
        
        while( aux != null ){
            atual = aux; // Salva o No anterior
            aux = aux.getProx(); // Pega o próximo No
        }
        
        // No(int ID, int tamanho, No prox, int endereco) {
        No novo = new No(noID, tamanho, listaLivre.getInicio().getEndereco());
        
        // Função de loop na lista livre, aloca o processo no menor No que comporte o processo
        listaLivre.updateNo(tamanho);
        
        if( atual == null ) {
            this.inicio = novo;
        } else {
            atual.setProx(novo);
        }
        
        return listaLivre;
    }
    
    // Função a ser executada na LISTA LIVRE, encontra e atualiza o endereço do
    // menor No que comporta o processo.
    public void updateNo (int tamanho) {
        No aux = this.inicio;
        No atual = null;
        No selected = this.inicio;
        
        while( aux != null ){
            if (aux.getTamanho() >= tamanho && selected.getTamanho() > aux.getTamanho()) {
                selected = aux; // Selectiona o No que tem o tamanho apropriada para o processo
            }
            
            atual = aux; // Salva o No anterior
            aux = aux.getProx(); // Pega o próximo No
        }
        
        // Atualiza o endereço do No da LISTA LIVRE
        selected.setEndereco(selected.getEndereco() + tamanho);
        // Atualiza o tamanho do No da LISTA LIVRE
        selected.setTamanho(selected.getTamanho() - tamanho);
        
        if (selected.getTamanho() == 0) {
            selected = selected.getProx();
        }
    }
    
    // Adiciona um No em uma posição de maneira ordenada na lista
    // A ser executada na LISTA LIVRE
    public void addOrdenado(No noID) {
        No aux = this.inicio;
        No atual = null;
        
        while( aux != null && aux.getEndereco() < noID.getEndereco() ){
            atual = aux;
            aux = aux.getProx();
        }
        
        // No(No prox, int tamanho, int endereco)
        No novo = new No(aux, aux.getTamanho(), aux.getEndereco());
        
        if( atual == null )
            this.inicio = novo;
        else
            atual.setProx(novo);
    }
    
    // Metódo para ser utilizado na LISTA ALOCADA
    public int usedSpace() {
        No aux = this.inicio;
        int usedSpace = 0;
        
        while( aux != null ){
            usedSpace += aux.getTamanho(); // Retorna o tamanho do No
            aux = aux.getProx(); // Pega o próximo No
        }
                
        return usedSpace;
    }
    
    // Metódo para ser utilizado na LISTA LIVRE
    public int availableSpace() {
        No aux = this.inicio;
        int availableSpace = 0;
        
        while( aux != null ){
            availableSpace += aux.getTamanho(); // Retorna o tamanho do No
            aux = aux.getProx(); // Pega o próximo No
        }
                
        return availableSpace;
    }
    
    // Método para ser utilizado na LISTA ALOCADA
    public void endereco () {
        No aux = this.inicio;
        No atual = null;
        
        while( aux != null ){
            atual = aux; // Salva o No anterior
            System.out.println(atual.getEndereco());
            aux = aux.getProx(); // Pega o próximo No
        }
    }
    
    public No buscaID(int ID) {
        for(No aux = this.inicio; aux != null; aux = aux.getProx()) {
            if( aux.getID()== ID) {
                
                System.out.println("ENCONTREI!");
                return aux;
            }
        }
        
        System.out.println("FALHEI!");
        return null;
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
