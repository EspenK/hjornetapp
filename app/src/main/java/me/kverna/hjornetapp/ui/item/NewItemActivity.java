package me.kverna.hjornetapp.ui.item;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.math.BigDecimal;

import me.kverna.hjornetapp.R;

public class NewItemActivity extends AppCompatActivity {
    private NewItemViewModel newItemViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        newItemViewModel = ViewModelProviders.of(this, new NewItemViewModelFactory())
                .get(NewItemViewModel.class);

        final EditText titleEditText = findViewById(R.id.title);
        final EditText priceEditText = findViewById(R.id.price);
        final EditText descriptionEditText = findViewById(R.id.description);
        final Button newItemButton = findViewById(R.id.newItem);

        newItemViewModel.getTaskResult().observe(this, taskResult -> {
            if (taskResult == null) {
                return;
            }

            if (taskResult.getError() != null) {
                makeToast(taskResult.getError());
            }
            if (taskResult.getSuccess() != null) {
                makeToast(taskResult.getSuccess());
            }
        });

        newItemButton.setOnClickListener(v -> {
            newItemViewModel.newItem(
                    titleEditText.getText().toString(),
                    BigDecimal.valueOf(Double.parseDouble(priceEditText.getText().toString())),
                    descriptionEditText.getText().toString());
        });
    }

    private void makeToast(@StringRes Integer messageString) {
        Toast.makeText(getApplicationContext(), messageString, Toast.LENGTH_SHORT).show();
    }
}
