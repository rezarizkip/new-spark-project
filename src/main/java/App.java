import com.google.gson.Gson;

import java.util.HashMap;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        //config
        port(8080);
        int maxThreads = 8;
        int minThreads = 2;
        int timeOutMillis = 30000;
        threadPool(maxThreads, minThreads, timeOutMillis);

        after((request, response) -> {
            response.header("Content-Type", "application/json");
        });

        get("/hello", (req, res) -> "Hello World!");

        get("/submit", (request, response) -> {
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String password = request.queryParams("password");

            //set response
            HashMap<String,String> result = new HashMap<>();
            result.put("status", "success");
            result.put("message", "data masuk " + name + ", " + email + ", " + password);

            //set
            Gson gson = new Gson();

            return gson.toJson(result);
        });
    }
}
