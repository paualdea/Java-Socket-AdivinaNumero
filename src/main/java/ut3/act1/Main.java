package ut3.act1;

public class Main {
    // Definimos las constantes de direccion y puerto de los sockets
    final static String DIRECCION = "localhost";
    final static int PUERTO = 8080;

    public static void main(String[] args) {
        // Creamos el cliente del juego
        Cliente cliente = new Cliente(DIRECCION, PUERTO);

        /*
         Para el servidor, creamos un hilo separado para que no bloquee el hilo principal del main (con el accept()) esperando un cliente
         Creamos el servidor dentro del hilo con new Servidor()
         */
        Thread hiloServidor = new Thread(new Servidor(DIRECCION, PUERTO));
        hiloServidor.start();

        // Llamamos a la función para que el cliente juegue
        cliente.jugar();
    }
}