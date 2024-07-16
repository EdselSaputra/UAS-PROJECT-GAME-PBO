/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netcode.main;

/**
 *
 * @author HP
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class scoreManager {

    public static void saveGameScore(int userId, int score) {
        String sql = "INSERT INTO game_scores (user_id, score, play_date) VALUES (?, ?, CURRENT_TIMESTAMP)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, score);

            pstmt.executeUpdate();
            System.out.println("Game score saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving game score: " + e.getMessage());
        }
    }
}

