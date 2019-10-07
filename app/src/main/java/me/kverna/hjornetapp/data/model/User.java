package me.kverna.hjornetapp.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class User {
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String streetAddress;
    String postalArea;
    String postalCode;

    public User(JSONObject jsonObject) throws JSONException {
        setFirstName(jsonObject.getString("firstName"));
        setLastName(jsonObject.getString("lastName"));
        setEmail(jsonObject.getString("email"));
        setPhoneNumber(jsonObject.getString("phoneNumber"));
        setStreetAddress(jsonObject.getString("streetAddress"));
        setPostalArea(jsonObject.getString("postalArea"));
        setPostalCode(jsonObject.getString("postalCode"));
    }
}
