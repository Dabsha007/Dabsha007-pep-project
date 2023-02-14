package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    
    public static Account getAccount(String username) throws SQLException {

        PreparedStatement s = ConnectionUtil.getConnection().prepareStatement("SELECT * FROM account WHERE username = ?");
        s.setString(1, username);

        ResultSet result = s.executeQuery();

        while(result.next()) {
            return new Account(result.getInt("account_id"), result.getString("username"), result.getString("password"));

        }
        return null;
        
    }
    
    public static Account getAccount(String username, String password) throws SQLException {

        PreparedStatement s = ConnectionUtil.getConnection().prepareStatement("SELECT * FROM account WHERE username = ? AND password = ? ");
        s.setString(1, username);
        s.setString(2, password);

        ResultSet result = s.executeQuery();

        while(result.next()) {
            return new Account(result.getInt("account_id"), username, password);

        }
        return null;
        
    }

    public static void createAccount(String username, String password) throws SQLException {

        PreparedStatement s = ConnectionUtil.getConnection().prepareStatement("INSERT INTO account (`username`, `password`) VALUES (?, ?)");
        s.setString(1, username);
        s.setString(2, password);

        s.execute();

        
    }
}
