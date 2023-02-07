package Controller;

import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.RawData;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        app.post("/register", this::registerHandler);
        
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    private void registerHandler(Context ctx) throws JsonMappingException, JsonProcessingException{

        ObjectMapper om = new ObjectMapper();

        RawData data = om.readValue(ctx.body(), RawData.class);
        
        if(data.username.length() == 0) {
            ctx.status(400);
            ctx.result("Username is blank.");
        }
        else if (data.password.length() < 4) {
            ctx.status(400);
            ctx.result("Password is not long enough.");
        }
        else {
            Account account = null;
            try {
                account = AccountService.createAccount(data.username, data.password);

                ctx.json(account);
                System.out.println(account);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                ctx.status(400);
                ctx.result("Error");
            }
            
        }
    }


}