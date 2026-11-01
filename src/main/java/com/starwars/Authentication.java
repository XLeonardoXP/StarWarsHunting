package com.starwars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/star_wars_hunting";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "XD4taL3o@12XP";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public boolean register(String name, String surname, String username, String password) {
        String sql = "INSERT INTO users (name, surname, username, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, username);
            stmt.setString(4, password);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Errore di registrazione (username potrebbe essere già esistente): " + e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            // Se rs.next() è true, significa che è stato trovato un utente con quelle credenziali
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Errore durante il login: " + e.getMessage());
            return false;
        }
    }
}
