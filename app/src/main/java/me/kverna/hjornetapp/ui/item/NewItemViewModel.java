package me.kverna.hjornetapp.ui.item;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import me.kverna.hjornetapp.R;
import me.kverna.hjornetapp.data.LoginRepository;
import me.kverna.hjornetapp.data.Result;
import me.kverna.hjornetapp.ui.TaskResult;

public class NewItemViewModel extends ViewModel {
    private MutableLiveData<TaskResult<Integer>> taskResult = new MutableLiveData<>();

    public MutableLiveData<TaskResult<Integer>> getTaskResult() {
        return taskResult;
    }
    @SuppressLint("StaticFieldLeak")
    public void newItem(String title, BigDecimal price, String description) {
        new AsyncTask<Void, Void, Result>() {
            @Override
            protected Result doInBackground(Void... voids) {
                HttpURLConnection connection = null;

                LoginRepository loginRepository = LoginRepository.getInstance();

                if (loginRepository == null || !loginRepository.isLoggedIn()) {
                    return new Result.Error(new IOException("Need to be logged in to create new item"));
                }

                try {
                    URL url = new URL("http://192.168.1.87:8080/api/item/create");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setUseCaches(false);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Authorization", "Bearer " + loginRepository.getToken());

                    // create json object of the input data
                    JSONObject dataJson = new JSONObject();
                    dataJson.put("title", title);
                    dataJson.put("price", price);
                    dataJson.put("description", description);

                    // convert the json object to bytes and write it to the connection output stream
                    try (OutputStream outputStream = connection.getOutputStream()) {
                        byte[] dataByte = dataJson.toString().getBytes(StandardCharsets.UTF_8);
                        outputStream.write(dataByte);
                    }

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        return new Result.Success<>(R.string.newItemSuccess);
                    } else {
                        return new Result.Error(new IOException("Failed to create new item"));
                    }
                } catch (Exception e) {
                    return new Result.Error(new IOException("Error making a new item"));
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }

            @Override
            protected void onPostExecute(Result result) {
                TaskResult<Integer> tr = new TaskResult<>();
                if (result instanceof Result.Success) {
                    tr.setSuccess(R.string.newItemSuccess);
                } else {
                    tr.setError(R.string.newItemError);
                }
            }
        }.execute();
    }
}
