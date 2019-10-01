package io.tollefsen.fant.ui.home;

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

import io.tollefsen.fant.data.model.Sellable;

public class SellableViewModel extends AndroidViewModel {
    MutableLiveData<List<Sellable>> sellables;
    MutableLiveData<Sellable> selected = new MutableLiveData<>();

    RequestQueue requestQueue;

    public SellableViewModel(Application context) {
        super(context);
        requestQueue = Volley.newRequestQueue(context);
    }

    public LiveData<List<Sellable>> getSellables() {
        if(sellables == null) {
            sellables = new MutableLiveData<>();
            loadSellables();
        }

        return sellables;
    }

    public LiveData<Sellable> getSelected() {
        return selected;
    }


    public void setSelected(Sellable selected) {
        this.selected.setValue(selected);
    }

    protected void loadSellables() {
        String url = "http://158.38.101.138/api/fant";
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET,url,null,
            response -> {
                List<Sellable> sellables = new ArrayList<>();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        sellables.add(new Sellable(response.getJSONObject(i)));
                    }
                } catch (JSONException jex) {
                    System.out.println(jex);
                }
                this.sellables.setValue(sellables);
            }, System.out::println);
        requestQueue.add(jar);
    }
}
