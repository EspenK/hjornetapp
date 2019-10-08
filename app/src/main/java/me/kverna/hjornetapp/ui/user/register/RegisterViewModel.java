package me.kverna.hjornetapp.ui.user.register;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import me.kverna.hjornetapp.R;
import me.kverna.hjornetapp.data.Result;
import me.kverna.hjornetapp.ui.TaskResult;

import static me.kverna.hjornetapp.HjornetApi.AUTH_CREATE;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<TaskResult<Integer>> taskResult = new MutableLiveData<>();

    public MutableLiveData<TaskResult<Integer>> getTaskResult() {
        return taskResult;
    }
    @SuppressLint("StaticFieldLeak")
    public void register(
            String firstName,
            String lastName,
            String email,
            String password,
            String phoneNumber,
            String streetAddress,
            String postalArea,
            String postalCode) {
        new AsyncTask<Void, Void, Result>() {
            @Override
            protected Result doInBackground(Void... voids) {
                HttpURLConnection connection = null;

                try {
                    URL url = new URL(AUTH_CREATE);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setUseCaches(false);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");

                    // create json object of the input data
                    JSONObject dataJson = new JSONObject();
                    dataJson.put("firstName", firstName);
                    dataJson.put("lastName", lastName);
                    dataJson.put("email", email);
                    dataJson.put("password", password);
                    dataJson.put("phoneNumber", phoneNumber);
                    dataJson.put("streetAddress", streetAddress);
                    dataJson.put("postalArea", postalArea);
                    dataJson.put("postalCode", postalCode);

                    // convert the json object to bytes and write it to the connection output stream
                    try (OutputStream outputStream = connection.getOutputStream()) {
                        byte[] dataByte = dataJson.toString().getBytes(StandardCharsets.UTF_8);
                        outputStream.write(dataByte);
                    }

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        return new Result.Success<>(R.string.registerSuccess);
                    } else {
                        return new Result.Error(new IOException("Failed to register"));
                    }
                } catch (Exception e) {
                    return new Result.Error(new IOException("Failed to register"));
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
                    tr.setSuccess(R.string.registerSuccess);
                } else {
                    tr.setError(R.string.registerError);
                }
                taskResult.setValue(tr);
            }
        }.execute();
    }
}
