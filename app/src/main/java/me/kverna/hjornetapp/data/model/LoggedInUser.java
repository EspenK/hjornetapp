package me.kverna.hjornetapp.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private User user;
    private String token;

    public LoggedInUser(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getDisplayName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public String getToken() {
        return token;
    }
}
