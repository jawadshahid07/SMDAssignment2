package com.example.smdassignment2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity implements TaskInputFragment.OnTaskAddedListener {

    private ArrayList<Task> toDoTaskList;
    private ArrayList<Task> completedTaskList;
    private TaskAdapter toDoTaskAdapter;
    private TaskAdapter completedTaskAdapter;

    private ListView toDoListView;
    private ListView completedTasksListView;
    private TextView tvName;
    private TextView tvDescription;

    TodoFragment todoFragment;
    CompletedFragment completedFragment;
    FragmentManager fragmentManager;
    FloatingActionButton fabAddTask;

    Button btnTodo;
    Button btnCompleted;

    TaskInputFragment taskInputFragment;

    public void onTaskAdded(String taskName, String taskDescription) {
        Task newTask = new Task(taskName, taskDescription, false);
        toDoTaskList.add(newTask);
        toDoTaskAdapter.notifyDataSetChanged();
        saveTasks();
        btnCompleted.setVisibility(View.VISIBLE);
        btnTodo.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction()
                .show(todoFragment)
                .hide(completedFragment)
                .hide(taskInputFragment)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        fragmentManager = getSupportFragmentManager();
        todoFragment = (TodoFragment) fragmentManager.findFragmentById(R.id.todo_frag);
        completedFragment = (CompletedFragment) fragmentManager.findFragmentById(R.id.completed_frag);
        taskInputFragment = (TaskInputFragment) fragmentManager.findFragmentById(R.id.input_frag);

        btnTodo = findViewById(R.id.btnTodoList);
        btnCompleted = findViewById(R.id.btnCompletedList);

        fragmentManager.beginTransaction()
                .show(todoFragment)
                .hide(completedFragment)
                .hide(taskInputFragment)
                .commit();

        toDoTaskList = MyApplication.todoTasks;
        completedTaskList = MyApplication.completedTasks;

        toDoListView = todoFragment.getView().findViewById(R.id.lvTodoList);
        completedTasksListView = completedFragment.getView().findViewById(R.id.lvCompletedList);

        toDoTaskAdapter = new TaskAdapter(this, R.layout.single_list_item, toDoTaskList);
        toDoListView.setAdapter(toDoTaskAdapter);

        completedTaskAdapter = new TaskAdapter(this, R.layout.single_list_item, completedTaskList);
        completedTasksListView.setAdapter(completedTaskAdapter);

        fabAddTask = findViewById(R.id.fab_add_task);
        fabAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCompleted.setVisibility(View.GONE);
                btnTodo.setVisibility(View.GONE);
                fragmentManager.beginTransaction()
                        .hide(todoFragment)
                        .hide(completedFragment)
                        .show(taskInputFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .show(todoFragment)
                        .hide(completedFragment)
                        .hide(taskInputFragment)
                        .commit();
            }
        });

        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
                        .hide(todoFragment)
                        .show(completedFragment)
                        .hide(taskInputFragment)
                        .commit();
            }
        });
    }

    public void moveTaskToCompleted(Task task) {
        toDoTaskList.remove(task);
        task.setCompleted(true);
        completedTaskList.add(task);
        toDoTaskAdapter.notifyDataSetChanged();
        completedTaskAdapter.notifyDataSetChanged();
        saveTasks();
    }

    public void moveTaskToToDoList(Task task) {
        completedTaskList.remove(task);
        task.setCompleted(false);  // Mark task as incomplete
        toDoTaskList.add(task);
        toDoTaskAdapter.notifyDataSetChanged();
        completedTaskAdapter.notifyDataSetChanged();
        saveTasks();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTasks();
    }


    private void saveTasks() {
    }

    private void loadTasks() {

    }
}
