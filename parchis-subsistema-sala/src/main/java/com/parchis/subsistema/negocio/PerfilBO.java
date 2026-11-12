package com.parchis.subsistema.negocio;

import com.parchis.interfaces.dao.IPerfilDAO;
import com.parchis.interfaces.dto.PerfilDTO;
import com.parchis.subsistema.dao.PerfilDAOMYSQL;

public class PerfilBO {
    private IPerfilDAO perfilDAO;

    public PerfilBO() {
        // Inyección de la implementación de MySQL
        this.perfilDAO = new PerfilDAOMYSQL();
    }

    public void registrarJugador(int id, String nombre) {
        // Creamos el DTO para pasarlo al DAO
        PerfilDTO perfil = new PerfilDTO(id, nombre, 0);
        perfilDAO.guardarPerfil(perfil);
    }

    public PerfilDTO obtenerPerfilJugador(int id) {
        return perfilDAO.consultarPerfil(id);
    }
}