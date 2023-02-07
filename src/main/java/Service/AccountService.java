package Service;

import java.sql.SQLException;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    
    public static Account createAccount(String username, String password) throws SQLException {
        int accountID = AccountDAO.createAccount(username, password);

        return new Account(accountID, username, password);
    }

}