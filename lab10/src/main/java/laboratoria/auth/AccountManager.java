package laboratoria.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import laboratoria.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {

    private DatabaseConnection connection;

    public AccountManager(DatabaseConnection connection) {
        this.connection = connection;
    }

    public void register(String name, String password) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT * FROM auth_account WHERE name = ?;"
        );
        statement.setString(1, name);

        ResultSet result = statement.executeQuery();

        if (result.next()) { // Sprawdzanie czy użytkownik już istnieje
            System.out.println("User already exists");
            return;
        }

        statement = connection.getConnection().prepareStatement(
                "INSERT INTO auth_account (name, password) VALUES (?, ?);"
        );
        statement.setString(1, name);
        statement.setString(2, hashPassword(password));
        statement.executeUpdate();
        System.out.println("User registered successfully");
    }

    public boolean authenticate(String name, String password) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT * FROM auth_account WHERE name = ?;"
        );

        statement.setString(1, name);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String hashedPassword = resultSet.getString("password");
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
            if (result.verified) {
                System.out.println("User authenticated successfully");
                return true;
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("User not found");
        }
        return false;
    }

    public Account getAccount(String name) throws SQLException {
        PreparedStatement statement = connection.getConnection().prepareStatement(
                "SELECT * FROM auth_account WHERE name = ?;"
        );
        statement.setString(1, name);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Account(result.getInt("id"), result.getString("name"));
        }
        return null;
    }

    private String hashPassword(String password) {
        BCrypt.Hasher hasher = BCrypt.withDefaults();
        return hasher.hashToString(12, password.toCharArray());
    }
}
