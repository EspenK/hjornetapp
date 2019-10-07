package me.kverna.hjornetapp.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.os.AsyncTask;

import me.kverna.hjornetapp.data.LoginRepository;
import me.kverna.hjornetapp.data.Result;
import me.kverna.hjornetapp.data.model.LoggedInUser;
import me.kverna.hjornetapp.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        // can be launched in a separate asynchronous job
        new AsyncTask<Void, Void, Result>() {
            @Override
            protected Result doInBackground(Void... voids) {
                return loginRepository.login(email, password);
            }

            @Override
            protected void onPostExecute(Result result) {
                if (result instanceof Result.Success) {
                    LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
                    loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
                } else {
                    loginResult.setValue(new LoginResult(R.string.login_failed));
                }
            }
        }.execute();
    }

    public void loginDataChanged(String email, String password) {
        if (!isEmailValid(email)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder email validation check
    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        /*if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }*/
        return !email.trim().isEmpty();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 3;
    }
}
