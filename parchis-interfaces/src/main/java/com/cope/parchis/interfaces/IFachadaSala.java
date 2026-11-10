package interfaces;

import java.util.List;

public interface IFachadaSala {
    void solicitarLanzamientoDado(int idJugador);
    int obtenerValorDado();
    List<String> obtenerFichasMovibles(int idJugador);
    void seleccionarFicha(int idFicha);
    void moverFicha(int idFicha, int valorDado);
    
    void simularFlujoAlternoA2();
    void simularFlujoAlternoA3();
}