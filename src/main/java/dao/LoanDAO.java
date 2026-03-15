package dao;

import db.DatabaseConnection;

import java.sql.*;

public class LoanDAO {

    public void issueBook(int bookId, int readerId) {

        String sql = "INSERT INTO loans(book_id,reader_id,issue_date) VALUES(?,?,date('now'))";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            stmt.setInt(2, readerId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int bookId) {

        String sql = "UPDATE loans SET return_date=date('now') WHERE book_id=? AND return_date IS NULL";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showIssuedBooks() {

        String sql = """
                SELECT books.title, readers.name, issue_date
                FROM loans
                JOIN books ON books.id = loans.book_id
                JOIN readers ON readers.id = loans.reader_id
                WHERE return_date IS NULL
                """;

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                System.out.println(
                        rs.getString("title") +
                        " -> " +
                        rs.getString("name") +
                        " (" +
                        rs.getString("issue_date") +
                        ")"
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void popularBooks() {

        String sql = """
                SELECT books.title, COUNT(loans.book_id) as count
                FROM loans
                JOIN books ON books.id = loans.book_id
                GROUP BY books.title
                ORDER BY count DESC
                """;

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                System.out.println(
                        rs.getString("title") +
                        " | выдано: " +
                        rs.getInt("count")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
