package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.ConnectionUtil;

public class AccountDAO {
    

    public static int createAccount(String username, String password) throws SQLException {
        int ID = 0;

        Statement s = ConnectionUtil.getConnection().createStatement();
        boolean result = s.execute("INSERT INTO account (`username`, `password`) VALUES ('" + username + "', '" + password  + "')");
        if(result) {
            ResultSet set = s.executeQuery("SELECT * FROM account WHERE username='" + username + "'");

            ID = set.getInt(0);
        }
        return ID;
    }
}
