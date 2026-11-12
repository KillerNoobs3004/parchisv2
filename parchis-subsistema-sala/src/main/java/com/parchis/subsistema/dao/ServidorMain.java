
package com.parchis.subsistema.servidor;

import com.parchis.interfaces.dto.PerfilDTO;
import com.parchis.interfaces.dao.IPerfilDAO;
import com.parchis.subsistema.dao.PerfilDAOMYSQL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {

    public static void main(String[] args) {

        int puerto = 5000;

        System.out.println("==================================================");
        System.out.println(" [SERVIDOR] Iniciando subsistema de sala en puerto " + puerto + "...");
        System.out.println("==================================================");

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {

            System.out.println("[SERVIDOR] Escuchando conexiones de red...");

            while (true) {

                // Esperar conexión del cliente
                try (Socket clienteSocket = serverSocket.accept()) {

                    System.out.println("\n[RED] ¡Cliente conectado desde: "
                            + clienteSocket.getInetAddress() + "!");

                    // Canales de comunicación
                    BufferedReader lector = new BufferedReader(
                            new InputStreamReader(clienteSocket.getInputStream()));

                    PrintWriter escritor = new PrintWriter(
                            clienteSocket.getOutputStream(), true);

                    // Leer petición del cliente
                    String peticion = lector.readLine();

                    System.out.println("[RED] Petición recibida: " + peticion);

                    if (peticion != null &&
                            (peticion.contains("REALIZAR_TURNO")
                            || peticion.contains("ACTUALIZAR_ESTADO"))) {

                        System.out.println("[LOGICA] Procesando jugada y ejecutando CU-01...");

                        try {

                            // 1. Instanciar DAO de MySQL
                            IPerfilDAO perfilDao = new PerfilDAOMYSQL();

                            // 2. Crear DTO con los datos del jugador
                            PerfilDTO perfil = new PerfilDTO(
                                    1,
                                    "Jugador_Oliver",
                                    250
                            );

                            // 3. Guardar perfil en MySQL
                            boolean guardado = perfilDao.guardarPerfil(perfil);

                            // 4. Responder al cliente
                            if (guardado) {

                                escritor.println("TURNO_Y_BD_OK");

                                System.out.println(
                                        "[BD] ¡Perfil guardado en MySQL con éxito!");

                            } else {

                                escritor.println("ERROR_BD");

                                System.out.println(
                                        "[BD ERROR] No se pudo guardar el perfil.");

                            }

                        } catch (Exception e) {

                            escritor.println("ERROR_BD");

                            System.err.println(
                                    "[ERROR] Problema al guardar en MySQL: "
                                    + e.getMessage());

                            e.printStackTrace();
                        }

                    } else {

                        escritor.println("PETICION_DESCONOCIDA");

                        System.out.println(
                                "[SERVIDOR] La petición recibida no es válida.");
                    }

                } catch (Exception e) {

                    System.err.println(
                            "[ERROR] Problema atendiendo al cliente: "
                            + e.getMessage());
                }

                System.out.println(
                        "[RED] Conexión finalizada. Esperando nueva conexión...");
            }

        } catch (Exception e) {

            System.err.println(
                    "[ERROR CRITICO EN SERVIDOR]: " + e.getMessage());

            e.printStackTrace();
        }
    }
}
