package memorysimulator;

import java.util.Scanner;

public class MemorySimulator {
    public static listaLigada listaLivre, listaAlocada;

    public static void main(String[] args) {
        int processID, maximumSize = 5000, choice;
        boolean running = false;

        System.out.println("----------------------------------");
        System.out.println("     ALOCADOR DE MEMÓRIA 2020");
        System.out.println("----------------------------------");
        System.out.println("");

        listaLivre = new listaLigada(maximumSize);
        listaAlocada = new listaLigada();
        Scanner sc = new Scanner(System.in);

        // getInicio() retorna o PRIMEIRO No da lista
        // getProx() retorna o PRIMEIRO No da lista

        running = true;
        processID = 1; // Começo dos processos, IDs serão incrementais

        while(running) {
            System.out.println("----------------------------------------");
            System.out.println("- 1 - Para adicionar um processo.");
            System.out.println("- 2 - Para remover um processo.");
            System.out.println("- 3 - Para encerrar o programa.");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("- Qual o tamanho do processo que será iniciado?");
                    int memory = sc.nextInt();
                    insert(processID, memory);
                    processID++;
                    break;
                case 2:
                    System.out.printf("- Qual a indentificação do processo a ser removido?");
                    int ID = sc.nextInt();
                    remove(ID);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("- Código não reconhecido.");
                    break;
            }
            feedbackForUser();
        }
    }

    public static void feedbackForUser() {
        System.out.println(listaAlocada.toStringAlocada());
        System.out.println();
        System.out.println(listaLivre.toStringLivre());
    }

    public static void insert (int processID, int memory) {
        if ( listaLivre.availableSpace() > 0 ) {
            listaLivre = listaAlocada.addAlocada(processID, memory, listaLivre);
            listaLivre.clearNodes();

            System.out.println("- Processo: " + processID + " alocado com sucesso.");
            System.out.println("----------------------------------------");
            System.out.println("- Espaço disponível: " + listaLivre.availableSpace());
            System.out.println("- Espaço alocado: " + listaAlocada.usedSpace());
            System.out.println("----------------------------------------");
        } else {
            System.out.println("- Memória cheia. É necessário remover um processo.");
            System.out.println("----------------------------------------");
        }
    }

    public static void remove (int ID) {
        No no = listaAlocada.buscaERemove(ID);
        if (no != null) listaLivre.addOrdenado(no);
        // System.out.println("No: " + no.getID() + ", no endereço: " + no.getEndereco() + ", tamanho: " + no.getTamanho());
        if ( (no.getEndereco() + no.getTamanho()) == listaLivre.getInicio().getEndereco() ) {
            listaLivre.getInicio().setEndereco( no.getEndereco() );
        }
    }
}
