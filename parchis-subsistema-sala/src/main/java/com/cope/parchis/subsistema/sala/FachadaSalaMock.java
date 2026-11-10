package subsistema.sala;

import interfaces.IFachadaSala;
import java.util.ArrayList;
import java.util.List;

public class FachadaSalaMock implements IFachadaSala {
    
    private int ultimoValorDado = 0;

    @Override
    public void solicitarLanzamientoDado(int idJugador) {
        this.ultimoValorDado = (int) (Math.random() * 6) + 1;
        System.out.println("[MOCK] Jugador " + idJugador + " lanzó el dado. Resultado: " + ultimoValorDado);
    }

    @Override
    public int obtenerValorDado() {
        return this.ultimoValorDado;
    }

    @Override
    public List<String> obtenerFichasMovibles(int idJugador) {
        List<String> fichasMock = new ArrayList<>();
        fichasMock.add("Ficha_1");
        fichasMock.add("Ficha_2");
        System.out.println("[MOCK] Obteniendo fichas disponibles para el jugador " + idJugador);
        return fichasMock;
    }

    @Override
    public void seleccionarFicha(int idFicha) {
        System.out.println("[MOCK] Ficha seleccionada: " + idFicha);
    }

    @Override
    public void moverFicha(int idFicha, int valorDado) {
        System.out.println("[MOCK] Moviendo ficha " + idFicha + " " + valorDado + " casillas en la Pizarra virtual.");
    }

    @Override
    public void simularFlujoAlternoA2() {
        throw new UnsupportedOperationException("[MOCK A2] Error: La ficha seleccionada no cuenta con un movimiento válido.");
    }

    @Override
    public void simularFlujoAlternoA3() {
        throw new RuntimeException("[MOCK A3] Error de red simulado en la Pizarra.");
    }
}