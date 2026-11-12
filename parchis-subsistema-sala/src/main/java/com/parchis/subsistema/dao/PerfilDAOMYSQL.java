package com.parchis.subsistema.dao;

import com.parchis.interfaces.dao.IPerfilDAO;
import com.parchis.interfaces.dto.PerfilDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PerfilDAOMYSQL implements IPerfilDAO {
    // Configura aquí tu URL, usuario y contraseña de MySQL local
    private static final String URL = "jdbc:mysql://localhost:3306/parchis_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; 

    @Override
    public boolean guardarPerfil(PerfilDTO perfil) {
        String sql = "INSERT INTO perfil_jugador (id_jugador, nombre, puntuacion) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, perfil.getId());
            stmt.setString(2, perfil.getNombre());
            stmt.setInt(3, perfil.getPuntuacion());
            stmt.executeUpdate();
            System.out.println("[DAO] Perfil guardado exitosamente en MySQL.");
            return true;
        } catch (Exception e) {
            System.out.println("[DAO ERROR] Error al guardar perfil: " + e.getMessage());
            return false;
        }
    }

    @Override
    public PerfilDTO consultarPerfil(int id) {
        String sql = "SELECT id_jugador, nombre, puntuacion FROM perfil_jugador WHERE id_jugador = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PerfilDTO(rs.getInt("id_jugador"), rs.getString("nombre"), rs.getInt("puntuacion"));
            }
        } catch (Exception e) {
            System.out.println("[DAO ERROR] Error al consultar perfil: " + e.getMessage());
        }
        return null;
    }
}