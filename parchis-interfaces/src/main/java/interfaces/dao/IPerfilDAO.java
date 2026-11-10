package interfaces;

public interface IPerfilDAO {
    void guardarPerfil(String nombre, int idJugador);
    String consultarPerfil(int idJugador);
}
