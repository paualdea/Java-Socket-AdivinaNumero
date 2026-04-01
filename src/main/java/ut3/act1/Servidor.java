package ut3.act1;

// IMPORTS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Creamos la clase de servidor como Runnable para que pueda ser ejecutado en un hilo independiente
 */
public class Servidor implements Runnable {
    // Variables para almacenar la direccion y puerto del servidor
    String direccion;
    int puerto;

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
    }

    /**
     * Función ejecutada por el hilo
     */
    @Override
    public void run() {
        // Implementamos una estructura try-with-resources para optimizar los recursos y manejar excepciones
        try (
                // Creamos el socket servidor (ServerSocket) y el socket que contactara con el cliente (Socket)
                ServerSocket servidor = new ServerSocket(puerto);
                Socket socket = servidor.accept();

                // Creamos el lector y escritor que interactuán con el cliente
                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true)
        ){
            // Obtenemos el mensaje del cliente
            String mensajeCliente = lector.readLine();

            // Imprimimos por pantalla la respuesta del servidor
            String mensajeRespuesta = mensajeCliente + " recibido. OK";
            escritor.println(mensajeRespuesta);
            System.out.println("Servidor responde: " + mensajeRespuesta);

        }
        catch (IOException e) {
            System.err.println("Problema en la comunicación cliente-servidor: " + e.getLocalizedMessage());
        }
    }
}
