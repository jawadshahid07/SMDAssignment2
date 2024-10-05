package com.example.smdassignment2;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Task> todoTasks;
    public static ArrayList<Task> completedTasks;


    @Override
    public void onCreate() {
        super.onCreate();
        todoTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();

        todoTasks.add(new Task("Give Evaluation", "Prepare evaluation for 30 Sep 2024", false));
        completedTasks.add(new Task("Submit Project Report", "Project report due by 1 Oct 2024", true));
    }

    public void saveTasks() {

    }

    public void loadTasks() {

    }
}
