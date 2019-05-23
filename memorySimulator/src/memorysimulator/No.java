package memorysimulator;

public class No {
    private int ID;
    private int endereco = 0;
    public int tamanho;
    private No prox;

    public No(int ID, int tamanho, int endereco, No prox) {
        this.tamanho = tamanho;        
        this.prox = prox;
        this.ID = ID;
        
        if(endereco == 0) {
            this.endereco = 0;
        } else {
            this.endereco = endereco;
        }
    }

    public int getTamanho() {
        return tamanho;
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

    @Override
    public String toString() {
        if (this.prox == null)
            return "Processo " + this.ID + " esta utilizando " + this.tamanho + " de espaço a partir do endereco " + this.endereco + ".";
        else
            return "Processo " + this.ID + " esta utilizando " + this.tamanho + " de espaço a partir do endereco " + this.endereco + " \n" + this.prox;
    }
}
