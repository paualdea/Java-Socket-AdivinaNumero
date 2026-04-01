package ut3.act1;

// IMPORTS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Cliente {
    // Variables para almacenar la direccion y puerto del servidor
    String direccion;
    int puerto;

    /**
     * Creamos el objeto con sus parámetros en el constructor

     * @param direccion
     * Direccion (IP o localhost) a la que apunta el cliente
     * @param puerto
     * Puerto al que se conecta el cliente
     */
    public Cliente(String direccion, int puerto) {
        this.direccion = direccion;
        this.puerto = puerto;
    }

    /**
     * Función principal que ejecuta el juego desde el lado del cliente
     */
    public void jugar() {
        // Implementamos una estructura try-with-resources para optimizar los recursos y manejar excepciones
        try (
            // Creamos el socket apuntando a la direccion/puerto del servidor
            Socket socket = new Socket(direccion, puerto);

            // Creamos el escritor que interactua con el servidor
            PrintWriter escritura = new PrintWriter(socket.getOutputStream(), true);
        ){
            // Mandamos mensaje de prueba al servidor
            String mensaje = "prueba prueba";
            escritura.println(mensaje);
            System.out.println("Cliente envia: " + mensaje);
        }
        catch (IOException e) {
            System.err.println("Problema en la comunicación cliente-servidor: " + e.getLocalizedMessage());
        }
    }
}