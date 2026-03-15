package dao;

import db.DatabaseConnection;
import model.Reader;

import java.sql.*;

public class ReaderDAO {

    public void addReader(Reader reader) {

        String sql = "INSERT INTO readers(name,email) VALUES(?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reader.getName());
            stmt.setString(2, reader.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showReaders() {

        String sql = "SELECT * FROM readers";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
