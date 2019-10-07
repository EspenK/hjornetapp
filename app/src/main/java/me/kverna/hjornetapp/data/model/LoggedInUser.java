package me.kverna.hjornetapp.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String email;
    private String token;

    public LoggedInUser(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return getEmail();
    }

    public String getToken() {
        return token;
    }
}
