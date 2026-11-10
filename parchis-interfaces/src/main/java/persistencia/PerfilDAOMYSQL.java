package persistencia;

import interfaces.IPerfilDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PerfilDAOMYSQL implements IPerfilDAO {

    // ⚠️ RECOMENDACIÓN DE SEGURIDAD (Evita el error que cuesta puntos en GitHub):
    // No dejes contraseñas quemadas aquí si lo subes público. Usa variables de entorno o archivo config.
    private static final String URL = "jdbc:mysql://localhost:3306/parchis_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "tu_password"; // ¡Cuidado con subir esto a GitHub!

    @Override
    public void guardarPerfil(String nombre, int idJugador) {
        String sql = "INSERT INTO perfil_jugador (id_jugador, nombre) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJugador);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
            System.out.println("[DAO] Perfil guardado exitosamente en MySQL.");
        } catch (Exception e) {
            System.out.println("[DAO ERROR] Error al guardar perfil: " + e.getMessage());
        }
    }

    @Override
    public String consultarPerfil(int idJugador) {
        String sql = "SELECT nombre FROM perfil_jugador WHERE id_jugador = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJugador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (Exception e) {
            System.out.println("[DAO ERROR] Error al consultar perfil: " + e.getMessage());
        }
        return "Desconocido";
    }
}
