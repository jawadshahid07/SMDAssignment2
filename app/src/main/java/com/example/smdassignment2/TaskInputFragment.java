package com.example.smdassignment2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TaskInputFragment extends Fragment {

    EditText etTaskName, etTaskDescription;
    Button btnAddTask;

    public interface OnTaskAddedListener {
        void onTaskAdded(String taskName, String taskDescription);
    }


    private OnTaskAddedListener taskAddedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "TaskInputFragment onAttach called");
        if (context instanceof OnTaskAddedListener) {
            taskAddedListener = (OnTaskAddedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTaskAddedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "TaskInputFragment onCreateView called");
        View view = inflater.inflate(R.layout.fragment_task_input, container, false);

        etTaskName = view.findViewById(R.id.etTaskName);
        etTaskDescription = view.findViewById(R.id.etTaskDescription);
        btnAddTask = view.findViewById(R.id.btnAddTask);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = etTaskName.getText().toString();
                String taskDescription = etTaskDescription.getText().toString();

                if (!taskName.isEmpty() && !taskDescription.isEmpty()) {
                    taskAddedListener.onTaskAdded(taskName, taskDescription);
                    etTaskName.setText("");
                    etTaskDescription.setText("");
                } else {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "TaskInputFragment onDetach called");
        taskAddedListener = null;
    }
}
