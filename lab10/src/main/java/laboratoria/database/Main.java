package laboratoria.database;

import laboratoria.auth.AccountManager;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        db.connect("lib/shop.db");
        //db.disconnect();

        AccountManager am = new AccountManager(db);
        //am.register("test", "test123");
        System.out.println(am.authenticate("test", "test123"));
        System.out.println(am.getAccount("test"));
    }
}