package com.miempresa.parchis.presentacion;

import fabrica.FabricaFachada;
import interfaces.IFachadaSala;
import red.PrototipoPizarraRed;

public class PrincipalTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- INICIANDO PRUEBA DE ARQUITECTURA Y RED HITO 2A ---");
        
        // 1. Probar la fachada y el mock
        IFachadaSala fachada = FabricaFachada.crearFachadaSala();
        fachada.solicitarLanzamientoDado(1);
        int resultado = fachada.obtenerValorDado();
        System.out.println("Valor obtenido del dado: " + resultado);
        
        // 2. Probar el prototipo de red del estilo Pizarra (Puerto 5000)
        int puertoTest = 5000;
        PrototipoPizarraRed.iniciarServidorPizarra(puertoTest);
        
        // Damos un pequeño respiro para que el hilo del servidor arranque
        Thread.sleep(500);
        
        // Simulamos el envío de una actualización de estado de la ficha hacia la Pizarra
        PrototipoPizarraRed.enviarMensajeAPizarra("127.0.0.1", puertoTest, "ACTUALIZAR_ESTADO: Ficha_1 movida a casilla " + resultado);
        
        // Esperamos un segundo para ver la respuesta en consola
        Thread.sleep(1000);
        System.out.println("--- PRUEBA DE RED EXITOSA ---");
    }
}