package io.tollefsen.fant.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Sellable {
    Long id;
    String title;
    String description;
    BigDecimal price;

    List<String> photos = new ArrayList<>();
    User seller;
    User buyer;

    public Sellable(JSONObject job) throws JSONException {
        setId(job.getLong("id"));
        setTitle(job.getString("title"));
        setDescription(job.getString("description"));
        setPrice(new BigDecimal(job.getString("price")));

        if(job.has("photos")) {
            JSONArray photos = job.getJSONArray("photos");
            for (int i = 0; i < photos.length(); i++) {
                JSONObject photo = photos.getJSONObject(i);
                this.photos.add(photo.getString("subpath"));
            }
        }

        if(job.has("seller")) {
            seller = new User(job.getJSONObject("seller"));
        }

        if(job.has("buyer")) {
            buyer = new User(job.getJSONObject("buyer"));
        }
    }
}
