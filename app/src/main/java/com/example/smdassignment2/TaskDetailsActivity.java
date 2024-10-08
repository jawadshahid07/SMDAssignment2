package com.example.smdassignment2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.smdassignment2.databinding.ActivityTaskDetailsBinding;

public class TaskDetailsActivity extends AppCompatActivity {

    private TextView tvNameText;
    private TextView tvDescriptionText;
    private TextView tvStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "TaskDetailsActivity onCreate called");
        setContentView(R.layout.activity_task_details);

        tvNameText = findViewById(R.id.tvNameText);
        tvDescriptionText = findViewById(R.id.tvDescriptionText);
        tvStatusText = findViewById(R.id.tvStatusText);

        Intent intent = getIntent();
        if (intent != null) {
            String taskName = intent.getStringExtra("taskName");
            String taskDescription = intent.getStringExtra("taskDescription");
            boolean completed = intent.getBooleanExtra("completedStatus", false);

            tvNameText.setText(taskName);
            tvDescriptionText.setText(taskDescription);

            if (completed) {
                tvStatusText.setText("Completed");
            }
            else {
                tvStatusText.setText("Pending");
            }
        }
    }
}
