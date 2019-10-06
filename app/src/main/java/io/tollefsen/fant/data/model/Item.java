package io.tollefsen.fant.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Item {
    int id;
    String title;
    String description;
    BigDecimal price;
    User owner;
    User buyer;

    public Item(JSONObject jsonObject) throws JSONException {
        setId(jsonObject.getInt("id"));
        setTitle(jsonObject.getString("title"));
        setDescription(jsonObject.getString("description"));
        setPrice(new BigDecimal(jsonObject.getString("price")));

        if (jsonObject.has("owner")) {
            setOwner(new User(jsonObject.getJSONObject("owner")));
        }

        if (jsonObject.has("buyer")) {
            setBuyer(new User(jsonObject.getJSONObject("buyer")));
        }
    }
}
