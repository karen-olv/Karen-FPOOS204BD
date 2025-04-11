package Practica18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserCrud {
    private Connection conexion;

    public UserCrud() {
        conexion = ConexionMySQL.conectar();
    }

    public boolean insertarUsuario(String nombre, String correo, String contrasena) {
        String sqlInsertar = "INSERT INTO usuarios(Nombre, Correo, Contrasena) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlInsertar);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, contrasena);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
            return false;
        }
    }

    public ResultSet buscarPorID(int id) {
        String sqlBuscar = "SELECT * FROM usuarios WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlBuscar);
            ps.setInt(1, id);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar por ID: " + e.getMessage());
            return null;
        }
    }

    public ResultSet buscarTodos() {
        String sqlBuscar = "SELECT * FROM usuarios";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlBuscar);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar todos los usuarios: " + e.getMessage());
            return null;
        }
    }

    public ResultSet buscarPorCorreo(String correo) {
        String sqlBuscar = "SELECT * FROM usuarios WHERE Correo = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlBuscar);
            ps.setString(1, correo);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al buscar por correo: " + e.getMessage());
            return null;
        }
    }

    public boolean actualizarUsuario(int id, String nombre, String correo, String contrasena) {
        String sqlActualizar = "UPDATE usuarios SET Nombre = ?, Correo = ?, Contrasena = ? WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlActualizar);
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setString(3, contrasena);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(int id) {
        String sqlEliminar = "DELETE FROM usuarios WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sqlEliminar);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
            return false;
        }
    }
}