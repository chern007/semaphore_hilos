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
public class main_hilo {

    //declaramos tanto en numero de filosofos como el numero de palillos por cada filosofo
    //y el semaforo que regulará el uso de estos
    public final static int numeroFilosofos = 5;
    //Rparto de palillos por cada filosofo: f0    f1    f2    f3     f4
    public final static int[][] repartoPalillos = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 0}};

    //tendremos un semáforo por cada palillo para regular su uso
    public final static Semaphore[] arraySemaforos = new Semaphore[numeroFilosofos];

    public static void main(String[] args) {

        //creamos los objetos filosofos y los objetos semafororos, estos ultimos
        //los guardamos en el array correspondiente 
        for (int i = 0; i < numeroFilosofos; i++) {
        arraySemaforos[i]= new Semaphore(1);
        }

        //creamos los objetos filosofos que implementan la clase thread e iniciamos los hilos
        for (int i = 0; i < numeroFilosofos; i++) {
            new filosofo(i,arraySemaforos,repartoPalillos).start();
        }

    }

}
