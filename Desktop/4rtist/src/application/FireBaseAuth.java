package application;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FireBaseAuth {
    public String auth(String username, String password) throws Exception { 

        HttpURLConnection urlRequest = null;
        String token = null;

        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyDADusAFsb0081OjSQp1B8rTA6VgGiSXCc");
            urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setRequestMethod("POST");
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("{\"email\":\""+username+"\",\"password\":\""+password+"\",\"returnSecureToken\":true}");
            osw.flush();
            osw.close();

            urlRequest.connect();
            
            //JsonParser jp = new JsonParser(); //from gson
            //JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
            //JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
            JsonObject rootobj = JsonParser.parseReader(new InputStreamReader((InputStream) urlRequest.getContent())).getAsJsonObject();
            token = rootobj.get("registered").getAsString();
            
        } catch (Exception e) {
            return token = "Not Registered";
        } finally {
            urlRequest.disconnect();
        }
        return token;
    }
}
