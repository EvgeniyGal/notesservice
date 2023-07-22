package edu.goit.notesservice.auth;


import edu.goit.notesservice.db.Database;
import edu.goit.notesservice.util.FileCreator;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class RegistrationRequest {
    //- додає нового юзера
    public void create(String name, String password) throws Exception {
        String statement = "INSERT INTO users (name, password) VALUES (?, ?);";
        Connection connection = new Database().getConnection();
        PreparedStatement insertStmt = null;
        try {
            connection.setAutoCommit(false);
            insertStmt = connection.prepareStatement(statement);
            insertStmt.setString(1, name);
            insertStmt.setString(2, password);
            insertStmt.executeUpdate();
            connection.commit();
            statement = statement.replaceFirst("\\?", "'" + name + "'");
            statement = statement.replaceFirst("\\?", "'" + password + "'");
            new FileCreator(statement);
        } catch (Exception ex) {
            connection.rollback();
            throw new SQLException("Creating user failed");
        } finally {
            if (insertStmt != null) {
                insertStmt.close();
            }
            connection.close();
            connection.setAutoCommit(true);
        }
    }
}
