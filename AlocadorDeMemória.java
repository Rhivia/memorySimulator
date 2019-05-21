/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alocadordememória;

import java.util.Scanner;

/**
 *
 * @author martin.akretzschmar
 */
public class AlocadorDeMemória {
    public static listaLigada listaLivre, listaAlocada;
    
    public static void main(String[] args) {
        int memory, processID, maximumSize = 4000;
        boolean running = false;
        
        System.out.println("***************************************************");
        System.out.println("*           ALOCADOR DE MEMÓRIA 2020              *");
        System.out.println("***************************************************");
        System.out.println("*                                                 *");
        
        listaLivre = new listaLigada(maximumSize);
        listaAlocada = new listaLigada();
        Scanner sc = new Scanner(System.in);
        
        running = true;
        processID = 0;
        
        while(running) {    
            System.out.println("*                                                 *");
            System.out.println("-  Qual o tamanho do processo que será iniciado?  -");
            System.out.println("*                                                 *");
            
            memory = sc.nextInt();
            insert(memory, processID);
            tick();
            processID++;
        }
    }
    
    public static void tick () {
        System.out.println(listaLivre);
        System.out.println(listaAlocada);
    }
    
    public static void insert (int memory, int processID) {
        if ( listaLivre.getAvailableSpace() ) {
            listaLivre.addOrdenado(memory);
            listaAlocada.addOrdenado(memory);
            
            System.out.println("*                                                 *");
            System.out.println("-          Processo: " + processID + " alocado sucesso.           -");
            System.out.println("-            Espaço disponível: " + listaLivre.size + "              -");
            System.out.println("-            Espaço alocado:    " + listaAlocada.size + "              -");
            System.out.println("*                                                 *");
            
        } else {
            
            System.out.println("*                                                 *");
            System.out.println("-                 NÃO HÁ ESPAÇO.                  -");
            System.out.println("*                                                 *");
            
        }
    }
}
