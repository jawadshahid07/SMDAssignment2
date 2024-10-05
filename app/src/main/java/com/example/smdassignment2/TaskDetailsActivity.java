package com.example.smdassignment2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        tvNameText = findViewById(R.id.tvNameText);
        tvDescriptionText = findViewById(R.id.tvDescriptionText);

        Intent intent = getIntent();
        if (intent != null) {
            String taskName = intent.getStringExtra("taskName");
            String taskDescription = intent.getStringExtra("taskDescription");

            tvNameText.setText(taskName);
            tvDescriptionText.setText(taskDescription);
        }
    }
}
