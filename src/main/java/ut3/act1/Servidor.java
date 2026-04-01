package ut3.act1;

// IMPORTS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Random;

/**
 * Creamos la clase de servidor como Runnable para que pueda ser ejecutado en un hilo independiente
 */
public class Servidor implements Runnable {
    // Variables para almacenar la direccion y puerto del servidor
    String direccion;
    int puerto, numero;
    // Creamos una variable booleana para que el juego sepa cuando ha terminado
    String estado = "";

    /**
     * Creamos el objeto con sus parámetros en el constructor

     * @param direccion
     * Direccion (IP o localhost) del servidor
     * @param puerto
     * Puerto al que se escucha
     */
    public Servidor(String direccion, int puerto) {
        this.direccion = direccion;
        this.puerto = puerto;

        // Generamos un numero aleatorio entre 1 y 20
        Random random = new Random();
        numero = random.nextInt(20) + 1;
    }

    /**
     * Función ejecutada por el hilo
     */
    @Override
    public void run() {
        while (!estado.equals("acertado")) {
            // Implementamos una estructura try-with-resources para optimizar los recursos y manejar excepciones
            try (
                    // Creamos el socket servidor (ServerSocket) y el socket que contactara con el cliente (Socket)
                    ServerSocket servidor = new ServerSocket(puerto);
                    Socket socket = servidor.accept();

                    // Creamos el lector y escritor que interactuán con el cliente
                    BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true)
            ){
                // Obtenemos el numero mandado por el cliente y lo transformamos en entero
                String numeroClienteString = lector.readLine();

                // Si el lector no esta vacío, empezar a leerlo
                if (numeroClienteString != null) {
                    int numeroCliente = Integer.parseInt(numeroClienteString);

                    // si el numero del cliente es correcto, acabamos el programa
                    if (numeroCliente == numero) {
                        System.err.println("\nSERVIDOR: ¡Numero acertado! Era el " + numero);

                        // Establecemos estado en acertado para salir del bucle
                        estado = "acertado";
                        escritor.println(estado);
                        // Si el numero del cliente es mas pequeño que el correcto, mandamos el estado pequeño
                    } else if (numeroCliente < numero) {
                        System.err.println("SERVIDOR: El numero correcto es mas grande que " + numeroCliente);
                        estado = "pequeno";
                        escritor.println(estado);
                        // Por otro lado, si el numero del cliente es demasiado grande, mandamos estado grande
                    } else {
                        System.err.println("SERVIDOR: El numero correcto es mas pequeño que " + numeroCliente);
                        estado = "grande";
                        escritor.println(estado);
                    }
                }
                // En caso de que no podamos leer nada del cliente, devolvemos nulo para que no ocurra nada
                else {
                    escritor.println("nulo");
                }
            }
            catch (IOException e) {
                System.err.println("Problema en la comunicación cliente-servidor: " + e.getLocalizedMessage());
            }
        }
    }
}
