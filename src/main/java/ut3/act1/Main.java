package ut3.act1;

import java.util.Scanner;

public class Main {
    // Definimos las constantes de direccion y puerto de los sockets
    final static String DIRECCION = "localhost";
    final static int PUERTO = 8080;

    public static void main(String[] args) throws InterruptedException {
        boolean juegoFinalizado = false;

        // Mientras el jiego no haya finalizado por completo, repetimos el bucle
        while (!juegoFinalizado) {
            Scanner sc = new Scanner(System.in);
            String estado = "nulo";
            // Creamos el cliente del juego
            Cliente cliente = new Cliente(DIRECCION, PUERTO);

            /*
             Para el servidor, creamos un hilo separado para que no bloquee el hilo principal del main (con el accept()) esperando un cliente
             Creamos el servidor dentro del hilo con new Servidor()
             */
            Thread hiloServidor = new Thread(new Servidor(DIRECCION, PUERTO));
            hiloServidor.start();

            // Limpiamos la pantalla
            limpiarPantalla();
            System.out.println();

            // Llamamos a la función para que el cliente juegue
            while (!estado.equals("acertado")) {
                // Hacemos jugar al cliente
                cliente.jugar();
                // Ponemos una breve pausa de 250ms
                Thread.sleep(500);

                // Obtenemos el estado del juego basado en la respuesta del servidor al cliente
                estado = cliente.getEstado();
            }

            // Paramos el juego 2 segundos
            Thread.sleep(1250);

            // Preguntamos si queremos seguir jugando
            System.out.print("\nVOLVER A JUGAR: 1. SI | 2. NO: ");
            String opcion = sc.nextLine();

            // Si no se quiere seguir jugando, salir del bucle y terminal el programa
            if (!opcion.equals("1")) {
                juegoFinalizado = true;
            }
        }
    }

    /**
     * Esta función limpia la pantalla dependiendo del sistema operativo que tengas
     */
    public static void limpiarPantalla() {
        try {
            // Obtenemos el sistema operativo desde el que se ejecuta el programa
            String so = System.getProperty("os.name").toLowerCase();

            // Si es windows lanzamos el comando cls para borrar la pantalla
            if (so.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Si es Linux o Mac, lanzamos una secuencia de caracteres ANSI que limpia y borra la pantalla
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Error al limpiar la pantalla.\n" + e.getMessage());
        }
    }
}