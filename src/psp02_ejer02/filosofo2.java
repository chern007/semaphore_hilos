/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp02_ejer02;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class filosofo2 extends Thread {

    //declaramos las propiedades del objeto filosofo
    int idFilosofo;

    Semaphore[] semaforosPalillos;

    int[][] palillosFilosofos;

    int palilloIzquierdo;

    int palilloDerecho;

    //constructor
    public filosofo2(int id_filosofo, Semaphore[] semaforos_palillos, int[][] palillos_filosofos) {

        this.idFilosofo = id_filosofo;
        this.palillosFilosofos = palillos_filosofos;
        this.semaforosPalillos = semaforos_palillos;

        //sacamos los palillos izquierdo y derecho
        this.palilloIzquierdo = palillos_filosofos[id_filosofo][0];
        this.palilloDerecho = palillos_filosofos[id_filosofo][1];
    }

    public void comer() {

        //intentamos coger el palillo de la izquierda
        if (semaforosPalillos[palilloIzquierdo].tryAcquire()) {

            if (semaforosPalillos[palilloDerecho].tryAcquire()) {

                System.out.println("El filósofo número " + idFilosofo + " está COMIENDO.");
                try {
                    sleep(new Random().nextInt(1000) + 800);//dormimos el hilo un tiempo aleatorio, que será lo que tarda en comer     
                } catch (InterruptedException ex) {
                    Logger.getLogger(filosofo2.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("El filósofo número " + idFilosofo + " ha TERMINADO de comer.");
                semaforosPalillos[palilloDerecho].release();//liberamos el palillo izquierdo

            }
            semaforosPalillos[palilloIzquierdo].release();//liberamos el palillo derecho
        } else {

            System.out.println("Los palillos del filósofo número " + idFilosofo + " están OCUPADOS");
        }

    }

    @Override
    public void run() {

        while (true) {
            try {
                sleep(500);//le damos tiempo antes de que vuelva a intentar empezar a comer de nuevo y no haya interbloqueo

            } catch (InterruptedException ex) {
                Logger.getLogger(filosofo2.class.getName()).log(Level.SEVERE, null, ex);
            }

            comer();
        }

    }

}
