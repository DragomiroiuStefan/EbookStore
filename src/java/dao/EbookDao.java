package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Author;
import model.Ebook;

public class EbookDao {

    public static List<Ebook> getAllEbooks() {
        String query = "SELECT * FROM EBOOKS";
        List<Ebook> ebooksList = new ArrayList<>();
        try (Connection connection = Utils.getDatabaseConnection();
                Statement stmt = connection.createStatement();
                ResultSet ebooks = stmt.executeQuery(query);) {
            while (ebooks.next()) {
                Ebook ebook = new Ebook();
                ebook.setIsbn(ebooks.getString(1));
                ebook.setName(ebooks.getString(2));
                ebook.setType(ebooks.getString(3));
                ebook.setQuality(ebooks.getString(4));
                ebook.setPages(ebooks.getString(5));
                ebook.setGenre(ebooks.getString(6));
                ebook.setAuthors(getAuthors(ebook.getIsbn(),connection));
                ebooksList.add(ebook);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EbookDao.class.getName()).log(Level.SEVERE, "Error retrieving all ebooks", ex);
        }
        return ebooksList;
    }

    private static List<Author> getAuthors(String ebookISBN, Connection connection) {
        String preparedQuery = "SELECT FIRST_NAME, FAMILY_NAME FROM EBOOKS_AUTHORS "
                             + "JOIN BOOK_AUTHORS ON EBOOKS_AUTHORS.ID_SSN=BOOK_AUTHORS.SSN "
                             + "WHERE ID_ISBN=?";
        List<Author> authorsList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(preparedQuery);) {
            stmt.setString(1, ebookISBN);
            ResultSet authors = stmt.executeQuery();
            while (authors.next()) {
                Author author = new Author();
                author.setFirstName(authors.getString(1));
                author.setFamilyName(authors.getString(2));
                authorsList.add(author);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EbookDao.class.getName()).log(Level.SEVERE, "Error retrieving authors for ebookISBN = " + ebookISBN, ex);
        }
        return authorsList;
    }
}
