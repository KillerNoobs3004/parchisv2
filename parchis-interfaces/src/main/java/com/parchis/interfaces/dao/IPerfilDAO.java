package com.parchis.interfaces.dao;

import com.parchis.interfaces.dto.PerfilDTO;

public interface IPerfilDAO {
    boolean guardarPerfil(PerfilDTO perfil);
    PerfilDTO consultarPerfil(int id);
}