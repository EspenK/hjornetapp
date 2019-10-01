package io.tollefsen.fant.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class User {
    String userid;

    public User(JSONObject job) throws JSONException {
        if (job.has("userid"))
            setUserid(job.getString("userid"));
    }
}
