package me.kverna.hjornetapp.ui.item.newitem;

import android.app.Activity;
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
    private EditText titleEditText;
    private EditText priceEditText;
    private EditText descriptionEditText;
    private Button newItemButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        newItemViewModel = ViewModelProviders.of(this, new NewItemViewModelFactory())
                .get(NewItemViewModel.class);

        titleEditText = findViewById(R.id.title);
        priceEditText = findViewById(R.id.price);
        descriptionEditText = findViewById(R.id.description);
        newItemButton = findViewById(R.id.newItem);

        newItemViewModel.getTaskResult().observe(this, taskResult -> {
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
