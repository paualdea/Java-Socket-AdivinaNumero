package ut3.act1;

// IMPORTS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;

public class Cliente {
    // Variables para almacenar la direccion y puerto del servidor
    String direccion, estado;
    int puerto, min, max;

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

        // Declaramos los valores máximos y minimos
        min = 1;
        max = 20;
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
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            // Creamos un numero aleatorio en base a lo recibido por el servidor
            int numeroAleatorio = ThreadLocalRandom.current().nextInt(min, max + 1);

            // Mandamos el numero al servidor y lo imprimimos por pantalla
            System.out.println("CLIENTE: Creo que es el " + numeroAleatorio);
            escritura.println(numeroAleatorio);

            // Obtenemos el estado recibido por el servidor
            estado = lector.readLine();

            // Si el numero generado es demasiado pequeño, el nuevo minimo es el numero generado +1
            if (estado.equals("pequeno")) {
                min = numeroAleatorio+1;
            // Por otro lado, si el numero generado es demasiado grande, el máximo será el numero generado -1
            } else if (estado.equals("grande")) {
                max = numeroAleatorio-1;
            }
        }
        catch (IOException e) {
            System.err.println("Problema en la comunicación cliente-servidor: " + e.getLocalizedMessage());
        }
    }

    // GETTER Y SETTER
    public String getEstado() {
        return estado;
    }
}