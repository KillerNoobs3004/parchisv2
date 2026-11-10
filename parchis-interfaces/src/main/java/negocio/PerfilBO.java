package negocio;

import interfaces.IPerfilDAO;
import persistencia.PerfilDAOMYSQL;

public class PerfilBO {
    private IPerfilDAO perfilDAO;

    public PerfilBO() {
        // Inyección del DAO a través de su interfaz
        this.perfilDAO = new PerfilDAOMYSQL();
    }

    public void registrarJugador(int id, String nombre) {
        perfilDAO.guardarPerfil(nombre, id);
    }

    public String obtenerNombreJugador(int id) {
        return perfilDAO.consultarPerfil(id);
    }
}
