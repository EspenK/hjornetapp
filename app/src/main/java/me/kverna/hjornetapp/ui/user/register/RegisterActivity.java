package me.kverna.hjornetapp.ui.user.register;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import me.kverna.hjornetapp.R;

public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel registerViewModel;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText phoneNumberEditText;
    private EditText streetAddressEditText;
    private EditText postalAreaEditText;
    private EditText postalCodeEditText;
    private Button register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        registerViewModel = ViewModelProviders.of(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        phoneNumberEditText = findViewById(R.id.phoneNumber);
        streetAddressEditText = findViewById(R.id.streetAddress);
        postalAreaEditText = findViewById(R.id.postalArea);
        postalCodeEditText = findViewById(R.id.postalCode);

        register = findViewById(R.id.newItem);

        registerViewModel.getTaskResult().observe(this, taskResult -> {
            if (taskResult == null) {
                return;
            }

            if (taskResult.isSuccess()) {
                makeToast(taskResult.getSuccess());
                setResult(Activity.RESULT_OK);
                finish();
            } else {
                makeToast(taskResult.getError());
            }
        });

        register.setOnClickListener(v -> {
            registerViewModel.register(
                    firstNameEditText.getText().toString(),
                    lastNameEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    phoneNumberEditText.getText().toString(),
                    streetAddressEditText.getText().toString(),
                    postalAreaEditText.getText().toString(),
                    postalCodeEditText.getText().toString()
            );
        });
    }

    private void makeToast(@StringRes Integer messageString) {
        Toast.makeText(getApplicationContext(), messageString, Toast.LENGTH_SHORT).show();
    }
}
