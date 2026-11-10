package red;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PrototipoPizarraRed {
    
    // Método para iniciar el servidor (La Pizarra central que recibe el estado)
    public static void iniciarServidorPizarra(int puerto) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                System.out.println("[PIZARRA-SERVIDOR] Escuchando actualizaciones en el puerto " + puerto + "...");
                while (true) {
                    Socket cliente = serverSocket.accept();
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    String mensajePizarra = entrada.readLine();
                    System.out.println("[PIZARRA-SERVIDOR] Estado recibido en la Pizarra: " + mensajePizarra);
                    cliente.close();
                }
            } catch (Exception e) {
                System.out.println("[SERVIDOR ERROR] " + e.getMessage());
            }
        }).start();
    }

    // Método para enviar un mensaje/estado desde un cliente (Agente) hacia la Pizarra
    public static void enviarMensajeAPizarra(String ipServidor, int puerto, String mensaje) {
        try (Socket socket = new Socket(ipServidor, puerto);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
            salida.println(mensaje);
            System.out.println("[CLIENTE-AGENTE] Mensaje enviado exitosamente a la Pizarra.");
        } catch (Exception e) {
            System.out.println("[CLIENTE ERROR] No se pudo conectar a la Pizarra: " + e.getMessage());
        }
    }
}
