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
import model.User;

public class UserDao {
    //TODO PREPARED STMT
    public static boolean insertUser(String ssn, String username, String password, String role) {
        String preparedQuery = "INSERT INTO USERS VALUES ("
                + "'" + ssn + "', "
                + "'" + username + "', "
                + "'" + password + "', "
                + "'" + role + "'"
                + ")";
        try (Connection conn = Utils.getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement(preparedQuery)) {
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error inserting user", ex);
            return false;
        }
    }
    
    public static String getPassword(String username) {
        String preparedQuery = "SELECT PASSWORD FROM USERS WHERE USERNAME=?";
        try (Connection conn = Utils.getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement(preparedQuery)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            if (result.next())
                return result.getString(1);
            else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error retrieving password", ex);
            return null;
        }
    }
    
    public static String getRole(String username) {
        String preparedQuery = "SELECT \"ROLE\" FROM USERS WHERE USERNAME=?";
        try (Connection conn = Utils.getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement(preparedQuery)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            if (result.next())
                return result.getString(1);
            else
                return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error retrieving role", ex);
            return null;
        }
    }
    
    public static List<User> getAllUsers() {
        String query = "SELECT * FROM USERS";
        List<User> usersList = new ArrayList<>();
        try (Connection connection = Utils.getDatabaseConnection();
                Statement stmt = connection.createStatement();
                ResultSet users = stmt.executeQuery(query);) {
            while (users.next()) {
                User user = new User();
                user.setSsn(users.getString(1));
                user.setUsername(users.getString(2));
                user.setRole(users.getString(4));
                usersList.add(user);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error retrieving all users", ex);
        }
        return usersList;
    }
    
    public static void deleteUSers(String[] ssn) {
        String preparedQuery = constructDeleteUsersQuery(ssn);
        try (Connection conn = Utils.getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement(preparedQuery)) {
            for (int i = 0; i < ssn.length; i++)
                stmt.setString(i+1, ssn[i]);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error deleting users", ex);
        }
    }
    
    private static String constructDeleteUsersQuery(String[] ssn) {
        StringBuilder preparedQuery = new StringBuilder("DELETE FROM USERS WHERE SSN IN (");
        preparedQuery.append("?");
        for (int i = 1; i < ssn.length; i++) 
            preparedQuery.append(", ?");
        preparedQuery.append(")");
        return preparedQuery.toString();
    }
    
    public static void updateRoles(String[] ssn, String role) {
        String preparedQuery = constructUpdateRolesQuery(ssn);
        try (Connection conn = Utils.getDatabaseConnection();
                PreparedStatement stmt = conn.prepareStatement(preparedQuery)) {
            stmt.setString(1, role);
            for (int i = 0; i < ssn.length; i++)
                stmt.setString(i+2, ssn[i]);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, "Error deleting users", ex);
        }
    }
    
    private static String constructUpdateRolesQuery(String[] ssn) {
        StringBuilder preparedQuery = new StringBuilder("UPDATE USERS SET \"ROLE\" = ? WHERE ssn IN (");
        preparedQuery.append("?");
        for (int i = 1; i < ssn.length; i++) 
            preparedQuery.append(", ?");
        preparedQuery.append(")");
        return preparedQuery.toString();
    }

}
