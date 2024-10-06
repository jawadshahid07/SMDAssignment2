package com.example.smdassignment2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;


public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private int resource;

    public TaskAdapter(@NonNull Context context, int resource, ArrayList<Task> taskList) {
        super(context, resource, taskList);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Task currentTask = getItem(position);
        TextView tvTaskName = convertView.findViewById(R.id.tvTaskName);
        ImageView ivCompleteIcon = convertView.findViewById(R.id.ivCompleteIcon);
        ImageView ivDeleteIcon = convertView.findViewById(R.id.ivDeleteIcon);

        tvTaskName.setText(currentTask.getName());


        ivCompleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTask.isCompleted()) {
                    currentTask.setCompleted(false);
                    ((ToDoListActivity) context).moveTaskToToDoList(currentTask);
                }
                else {
                    currentTask.setCompleted(true);
                    ((ToDoListActivity) context).moveTaskToCompleted(currentTask);
                }
            }
        });

        ivDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            ((ToDoListActivity) context).deleteTask(currentTask);
                            Toast.makeText(context, "Task deleted", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


        tvTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TaskDetailsActivity.class);
                intent.putExtra("taskName", currentTask.getName());
                intent.putExtra("taskDescription", currentTask.getDescription());
                ((ToDoListActivity) context).startActivityForResult(intent, 2);
            }
        });

        return convertView;
    }
}

