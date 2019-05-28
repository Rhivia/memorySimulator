// Jogos Digitais - Noturno
// 3º Semestre

// Martin A. Kretzschmar
// Felipe A. Nunes

package memorysimulator;

import java.util.Scanner;

public class MemorySimulator {
    public static listaLigada listaLivre, listaAlocada;

    public static void main(String[] args) {
        int processID, maximumSize = 5000, choice, memory;
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

        feedbackForUser();
        while(running) {
            System.out.println("----------------------------------------");
            System.out.println("- 1 - Para adicionar um processo.");
            System.out.println("- 2 - Para remover um processo.");
//            System.out.println("- 3 - Aumentar memória.");
            System.out.println("- 4 - Para encerrar o programa.");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("- Qual o tamanho do processo que será iniciado?");
                    memory = sc.nextInt();
                    if (maximumSize < memory) {
                        System.out.println("- Memória insuficiente. É necessário aumentar o total de memória.");
                        System.out.println("----------------------------------------");
                    } else if (listaLivre.availableSpace() < memory) {
                        System.out.println("- Memória insuficiente. É necessário finalizar um processo.");
                        System.out.println("----------------------------------------");
                    } else {
                        insert(processID, memory);
                        processID++;
                    }
                    feedbackForUser();
                    break;
                case 2:
                    System.out.printf("- Qual a indentificação do processo a ser removido?");
                    int ID = sc.nextInt();
                    remove(ID);
                    feedbackForUser();
                    break;
//                case 3:
//                    System.out.printf("- Quanto a memória aumentará?");
//                    memory = sc.nextInt();
//                    maximumSize += memory;
//                    System.out.println("- Tamanho atualizado para: " + maximumSize );
//                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("- Código não reconhecido.");
                    break;
            }
        }
    }

    public static void feedbackForUser() {
        System.out.println(listaAlocada.toStringAlocada());
        System.out.println();
        System.out.println(listaLivre.toStringLivre());
    }

    public static void insert (int processID, int memory) {
        listaLivre = listaAlocada.addAlocada(processID, memory, listaLivre);

        System.out.println("- Processo: " + processID + " alocado com sucesso.");
        System.out.println("-------------------------------------------------");
        System.out.println("- Espaço disponível: " + listaLivre.availableSpace());
        System.out.println("- Espaço alocado: " + listaAlocada.usedSpace());
        System.out.println("-------------------------------------------------");

    }

    public static void remove (int ID) {
        No no = listaAlocada.buscaERemove(ID);
        if (no != null) listaLivre.addOrdenado(no);
        listaLivre.clearNodes();
    }
}
