package io.tollefsen.fant.data;

import org.json.JSONObject;

import io.tollefsen.fant.data.model.LoggedInUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String email, String password) {
        HttpURLConnection c = null;
        try {
            URL url = new URL("http://192.168.1.87:8080/api/auth/login?email=" + email + "&password=" + password);
            c = (HttpURLConnection) url.openConnection();
            c.setUseCaches(true);
            c.setRequestMethod("GET");

            if(c.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), StandardCharsets.UTF_8));
                JSONObject responseJson = new JSONObject(br.readLine());
                String token = responseJson.getString("token");
                LoggedInUser fakeUser = new LoggedInUser(email, token);
                c.getInputStream().close(); // Why?
                return new Result.Success<>(fakeUser);
            }
            // TODO: handle loggedInUser authentication
            return new Result.Error(new IOException("Error logging in " + c.getResponseMessage()));
        } catch (Exception e) {
            System.err.println("Failed to call " + e);
            return new Result.Error(new IOException("Error logging in", e));
        } finally {
            if(c != null) c.disconnect();
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
