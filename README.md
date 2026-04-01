# Programación en Java: Sockets y Estructura Cliente-Servidor

Este proyecto ha sido desarrollado como parte de la **Actividad 1** de la Unidad de Trabajo 3 (UT3): "Programación de comunicaciones en red".

El programa implementa un sistema **cliente-servidor** utilizando sockets TCP para ejecutar un juego interactivo. El sistema permite que un cliente automático intente adivinar un número secreto generado por el servidor (1-20), recibiendo respuestas del servidor para ajustar el rango de números.

## Características Principales

* **Modelo Cliente-Servidor**: Implementación de estructura en dónde el servidor y el cliente interactúan mediante `BufferedReader` y `PrintWriter`.
* **Sockets TCP**: Uso de las clases `Socket` y `ServerSocket` del paquete `java.net` para realizar una comunicación de red entre los dos objetos (`Servidor` y `Cliente`).
* **Programación Multihilo**: Implementación de la interfaz `Runnable` en la clase `Servidor` para gestionarlo en un hilo independiente y no corte el hilo principal de la clase `Main`.
* **Gestión Eficiente de Recursos**: Uso de estructuras `try-with-resources` para asegurar el cierre automático de todos los objetos y  sockets, optimizando al máximo el uso de recursos.

## Funcionamiento

El programa sigue el siguiente flujo:

1. **Inicio**: El programa crea los objetos servidor y cliente y los ejecuta por primera vez.
2. **Creación**: El servidor crea un numero (1-20) y abre un puerto de escucha (8080) mediante el `Socket`.
2. **Conexión**: El cliente inicia la conexión con el servidor que acepta la petición y establece la comunicación.
3. **Protocolo de Comunicación**: 
    * El cliente genera una apuesta basada en un rango que se actualiza segun resultados anteriores (`min` y `max`).
    * El servidor recibe el número, lo compara con el secreto y responde con un estado: `"pequeno"`, `"grande"` o `"acertado"`.
    * El cliente procesa la respuesta para ajustar el rango del número en el siguiente intento.
4. **Finalización**: Una vez detectado el acierto, el servidor notifica al cliente y el juego termina. Luego, se permite iniciar una nueva partida.

## Instrucciones de Uso

Para ejecutar este programa, se puede usar el fichero `.jar` o ejecutar `Main.java` del código fuente desde las [*releases*](https://git.paualdea.com/paualdea/Java-Socket-AdivinaNumero/-/releases) del proyecto.

---
Este proyecto sirve como evidencia del aprendizaje sobre el desarrollo de aplicaciones distribuidas y comunicaciones en red en Java para la asignatura de **Programación de Servicios y Procesos**.