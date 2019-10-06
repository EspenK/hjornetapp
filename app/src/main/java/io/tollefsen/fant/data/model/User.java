package io.tollefsen.fant.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class User {
    String email;

    public User(JSONObject job) throws JSONException {
        if (job.has("email"))
            setEmail(job.getString("email"));
    }
}
