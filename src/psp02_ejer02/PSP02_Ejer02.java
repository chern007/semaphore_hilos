/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp02_ejer02;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Carlos
 */
public class PSP02_Ejer02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear semáforos. A cada uno se le da un único permiso:
        for (int i = 0; i < numeroFilosofos; i++) {
            // Sólo 1 permiso porque cada palillo-semaforo solo puede tenerlo un filósofo a la vez.
            palillos_semaforo[i] = new Semaphore(1);
        }
 
        // Crear los objetos de tipo Filosofo que extienden Thread, e iniciarlos.
        // Al constructor se le pasa un id, el array de los semaforos
        // y el array de sus 2 palillos:
        for (int idFilosofo = 0; idFilosofo < numeroFilosofos; idFilosofo++) {
            new filosofo(idFilosofo, palillos_semaforo, palillosFilosofo).start();
        }

    }
    
    /**
     * La cantidad de filósofos
     */
    final static int numeroFilosofos = 5;
 
    /**
     * Array de 2 dimensiones que tiene tantas filas como filósofos. Cada fila
     * es un un array de 2 enteros que representan los 2 palillos del filósofo:
     * el de su izquierda y el de su derecha, por este orden.
     */
    final static int[][] palillosFilosofo = {
        {0, 1}, // filosofo 0
        {1, 2}, // filosofo 1
        {2, 3}, // filosofo 2
        {3, 4}, // filosofo 3
        {4, 0} // filosofo 4
    };
 
    /**
     * Array de objetos Semaphore que representan a los palillos. Hay tantos
     * palillos como filósofos.
     */
    final static Semaphore[] palillos_semaforo = new Semaphore[numeroFilosofos];
    
}
