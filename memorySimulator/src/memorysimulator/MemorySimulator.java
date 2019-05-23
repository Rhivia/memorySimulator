package memorysimulator;

import java.util.Scanner;

public class MemorySimulator {
    public static listaLigada listaLivre, listaAlocada;
    public static int enderecoAux = 0;
    
    public static void main(String[] args) {
        int memory, processID, maximumSize = 4000, choice;
        boolean running = false;
        
        System.out.println("----------------------------------");
        System.out.println("     ALOCADOR DE MEMÓRIA 2020");
        System.out.println("----------------------------------");
        System.out.println("");
        
        listaLivre = new listaLigada(maximumSize);
        listaAlocada = new listaLigada();
        Scanner sc = new Scanner(System.in);
        
        running = true;
        processID = 0;
        
        while(running) {
            System.out.println("----------------------------------------");
            System.out.println("- 1 - Para adicionar um processo.");
            System.out.println("- 2 - Para remover um processo.");
            System.out.println("- 3 - Para encerrar o programa.");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("- Qual o tamanho do processo que será iniciado?");
                    memory = sc.nextInt();    
                    insert(processID, memory);
                    tick();
                    processID++;
                    break;
                case 2:
                    System.out.println("- Ainda não implementado.");
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("- Código não reconhecido.");
                    break;
            }
        }
    }
    
    public static void tick () {
        System.out.println(listaAlocada);
    }
    
    public static void insert (int processID, int memory) {
        if ( listaLivre.getAvailableSpace() ) {
            listaAlocada.add(processID, memory, listaLivre);
            // getInicio() retorna o PRIMEIRO No da lista
            // getProx() retorna o PRIMEIRO No da lista
            if ( listaAlocada.getInicio().getProx() != null ) {
                listaAlocada.getInicio().setEndereco(listaAlocada.getInicio().getProx().getEndereco());
            } else {
                listaAlocada.getInicio().setEndereco(listaAlocada.getInicio().getEndereco());
            }
            
            listaLivre.getInicio().setEndereco(listaAlocada.getInicio().getEndereco());
            
            listaLivre.size -= memory;
            listaAlocada.size += memory;
            
            System.out.println("- Processo: " + processID + " alocado sucesso.");
            System.out.println("----------------------------------------");
            System.out.println("- Espaço disponível: " + listaLivre.size);
            System.out.println("- Espaço alocado: " + listaAlocada.size);
            System.out.println("----------------------------------------");
            
            enderecoAux += memory;
        } else {
            System.out.println("- Memória cheia. É necessário remover um processo.");
            System.out.println("----------------------------------------");
        }
    }
}
