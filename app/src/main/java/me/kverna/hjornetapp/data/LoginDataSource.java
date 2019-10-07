package me.kverna.hjornetapp.data;

import org.json.JSONObject;

import me.kverna.hjornetapp.data.model.LoggedInUser;
import me.kverna.hjornetapp.data.model.User;

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
        String token;
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://192.168.1.87:8080/api/auth/login?email=" + email + "&password=" + password);
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(true);
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                JSONObject responseJson = new JSONObject(br.readLine());
                token = responseJson.getString("token");
                connection.getInputStream().close();
            } else {
                return new Result.Error(new IOException("Error logging in " + connection.getResponseMessage()));
            }
        } catch (Exception e) {
            System.err.println("Failed to call " + e);
            return new Result.Error(new IOException("Error logging in", e));
        } finally {
            if(connection != null) connection.disconnect();
        }

        try {
            URL url = new URL("http:192.168.1.87:8080/api/auth/currentuser");
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                JSONObject responseJson = new JSONObject(br.readLine());
                connection.getInputStream().close();

                LoggedInUser loggedInUser = new LoggedInUser(new User(responseJson), token);
                return new Result.Success<>(loggedInUser);
            } else {
                return new Result.Error(new IOException("Error logging in " + connection.getResponseMessage()));
            }

        } catch (Exception e) {
            return new Result.Error(new IOException("Error getting current user"));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
