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
    public String toStringAlocada() {
        System.out.println("Processos: ");
        if (this.inicio != null)
            return this.inicio.toStringAlocada() + "";
        else 
            return "Não há nenhum processo em execução";
    }

    // Para exeibição dos nós
    public String toStringLivre() {
        System.out.println("Memórias livres: ");
        return this.inicio.toStringLivre() + "";
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
        listaLivre.useBestNode(tamanho);

        if( atual == null ) {
            this.inicio = novo;
        } else {
            atual.setProx(novo);
        }

        return listaLivre;
    }

    // Reorganiza Nós caso encontre um que não tenha mais espaço
    public void clearNodes(){
        No aux = this.inicio;

        while( aux != null ){
            System.out.println("No: " + aux.getID());
            if (aux.getTamanho() == 0) {
                aux.setProx(aux.getProx()); // Selectiona o No que tem o tamanho apropriada para o processo
            }

            aux = aux.getProx(); // Pega o próximo No
        }
    }

    // Função a ser executada na LISTA LIVRE, encontra e atualiza o endereço do
    // menor No que comporta o processo.
    public void useBestNode(int tamanho) {
        No aux = this.inicio;
        No selected = this.inicio;

        while( aux != null ){
            if (aux.getTamanho() >= tamanho && selected.getTamanho() > aux.getTamanho()) {
                selected = aux; // Selectiona o No que tem o tamanho apropriada para o processo
            }

            aux = aux.getProx(); // Pega o próximo No
        }

        // Atualiza o endereço do No da LISTA LIVRE
        selected.setEndereco(selected.getEndereco() + tamanho);
        // Atualiza o tamanho do No da LISTA LIVRE
        selected.setTamanho(selected.getTamanho() - tamanho);

        if (selected.getTamanho() == 0) {
            selected = selected.getProx(); // Atualiza a referência para o próximo No
        }
    }

    // Adiciona um No em uma posição de maneira ordenada na lista
    // A ser executada na LISTA LIVRE
    public void addOrdenado(No no) {
        No aux = this.inicio;
        No atual = null;

        while( aux != null && aux.getEndereco() < no.getEndereco() ){
            atual = aux;
            aux = aux.getProx();
        }

        // No(No prox, int tamanho, int endereco)
        No novo = new No(aux, no.getTamanho(), no.getEndereco());

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

    // Função para ser utilizada na LISTA ALOCADA
    public No buscaERemove(int ID) {
        No currentNode = this.getInicio();
        No nextNode = currentNode.getProx();
        
        System.out.println("Atual: " + currentNode.getID() + ", prox: " + nextNode.getID() + ", target: " + ID);
        if (nextNode.getProx() != null)
            System.out.println("Atual ID: " + currentNode.getProx().getID() + ", prox ID: " + nextNode.getProx().getID());

        // Busca a ID e sobrescreve o No pelo próximo No da Lista, efetivamente apagando-o
        while( currentNode != null ){            
            if( currentNode.getID() == ID) { // Se o valor do NODE seguinte for a ID
                No deadNode = nextNode; // Recebe o valor do NODE a ser removido
                nextNode = nextNode.getProx(); // Recebe o NODE apos o NODE avancado
                currentNode.setProx(nextNode); // Atualiza o valor do NODE atual para o NODE Avancado
                return deadNode; // Retorna o NODE que sera removido
                
            } else if( nextNode.getID() == ID) { // Se o valor do NODE seguinte for a ID
                No deadNode = nextNode; // Recebe o valor do NODE a ser removido
                System.out.println("Node morto: " + deadNode.getID());
                if (nextNode.getProx() != null) {
                    nextNode = nextNode.getProx(); // Recebe o NODE apos o NODE avancado
                    System.out.println("Node a receber a indicacao: " + nextNode.getID());
                    currentNode.setProx(nextNode); // Atualiza o valor do NODE atual para o NODE Avancado
                } else {
                    System.out.println("Ultimo Node removido.");
                    currentNode.setProx(null); // Atualiza o valor do NODE atual para o NODE Avancado
                }
                return deadNode; // Retorna o NODE que sera removido
            } else if( nextNode.getID() != ID ) {
                currentNode = currentNode.getProx();
                nextNode = currentNode.getProx();
            }
        }
        return currentNode; // Retorna o NODE nulo.
    }
    
    
    
    
    
    
    
    
    

    // Método para ser utilizado na LISTA ALOCADA
//    public void endereco () {
//        No nextNode = this.inicio;
//        No currentNode = null;
//
//        while( nextNode != null ){
//            currentNode = nextNode; // Salva o No anterior
//            System.out.println(currentNode.getEndereco());
//            nextNode = nextNode.getProx(); // Pega o próximo No
//        }
//    }
//
//    // versao recursiva
//    public boolean buscaRec(int x ){
//        return busca(x, this.inicio);
//    }
//
//    private boolean busca(int target, No nextNode){
//        // condicoes de parada
//        if( nextNode == null )
//            return false;
//
//        if( nextNode.getID() == target)
//            return true;
//
//        return busca(target, nextNode.getProx());
//    }
}
