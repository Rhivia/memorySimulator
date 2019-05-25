package memorysimulator;

public class No {
    private int ID;
    private int endereco = 0;
    public int tamanho;

    private No prox;

    // No inicial para LISTA LIVRE
    public No(int tamanho, int endereco) {
        this.ID = -1; // Nós da lista livre não possuem ID, usaremos -1 para isso.
        this.prox = null; // Como o construtor iniciará a lista, o próximo No é nulo.

        this.tamanho = tamanho;
        this.endereco = endereco; // Ao realizar o primeiro No, ele começaré com endereço 0.
    }

    // No inicial para LISTA ALOCADA
    // Adicionamos um No para o final da lista alocada, o proximo sempre será nulo
    public No(int ID, int tamanho, int endereco) {
        this.ID = ID; // ID incremental
        this.prox = null;

        this.tamanho = tamanho;
        this.endereco = endereco;
    }

    public No(No prox, int tamanho, int endereco) {
        this.ID = -1; // ID incremental
        this.prox = prox;

        this.tamanho = tamanho;
        this.endereco = endereco;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public int getID() {
        return ID;
    }

    public int getNo() {
        return this.endereco;
    }

    public String toStringAlocada() {
        if (this.prox == null)
            return "Processo ID: " + this.ID + " esta utilizando " + this.tamanho + " de memória. No endereço " + this.endereco;
        else
            return "Processo ID: " + this.ID + " esta utilizando " + this.tamanho + " de memória. No endereço " + this.endereco + "\n" + this.prox.toStringAlocada();
    }

    public String toStringLivre() {
        if (this.prox == null)
            return "Bloco de memória contendo " + this.tamanho + " de memória. Endereço: " + this.getEndereco();
        else
            return "Bloco de memória contendo " + this.tamanho + " de memória. Endereço: " + this.getEndereco() +  "\n" + this.prox.toStringLivre();
    }
}
