package me.kverna.hjornetapp.ui.item;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import me.kverna.hjornetapp.data.model.Item;

public class ItemViewModel extends AndroidViewModel {
    MutableLiveData<List<Item>> items;
    MutableLiveData<Item> selected = new MutableLiveData<>();

    RequestQueue requestQueue;

    public ItemViewModel(Application context) {
        super(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    public LiveData<List<Item>> getItems() {
        if(items == null) {
            items = new MutableLiveData<>();
            loadItems();
        }

        return items;
    }

    public LiveData<Item> getSelected() {
        return selected;
    }


    public void setSelected(Item selected) {
        this.selected.setValue(selected);
    }

    protected void loadItems() {
        String url = "http://192.168.1.87:8080/api/item/all";
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET,url,null,
            response -> {
                List<Item> items = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        items.add(new Item(response.getJSONObject(i)));
                    }
                } catch (JSONException jex) {
                    System.out.println(jex);
                }
                this.items.setValue(items);
            }, System.out::println);
        requestQueue.add(jar);
    }
}
