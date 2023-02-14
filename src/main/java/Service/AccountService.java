package Service;

import java.sql.SQLException;

import DAO.AccountDAO;
import Model.Account;
import io.javalin.http.Context;

public class AccountService {
    
    public static void createAccount(Context ctx, String username, String password) throws SQLException {
        Account account = AccountDAO.getAccount(username);

        if(account != null) {;
            ctx.status(400);
            ctx.result("Username taken");
        }
        else {
            AccountDAO.createAccount(username, password);
            account = AccountDAO.getAccount(username);

            ctx.json(account);
        }

    }
    public static void login(Context ctx, String username, String password) throws SQLException {
        Account account = AccountDAO.getAccount(username, password);

        if(account == null) {
            ctx.status(401);
            ctx.result("Error");
        }
        else {

            ctx.json(account);
        }

    }

}