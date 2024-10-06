package com.example.smdassignment2;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyApplication extends Application {

    public static ArrayList<Task> todoTasks;
    public static ArrayList<Task> completedTasks;

    private static final String FILE_NAME = "tasks.txt";

    @Override
    public void onCreate() {
        super.onCreate();
        todoTasks = new ArrayList<>();
        completedTasks = new ArrayList<>();

    }

    // Save tasks to a file
    public void saveTasks(Context context) {
        FileOutputStream fos = null;
        try {
            // Open or create the file
            File file = new File(context.getFilesDir(), FILE_NAME);
            fos = new FileOutputStream(file, false);  // 'false' to overwrite the file

            // Save todo tasks
            for (Task task : todoTasks) {
                String taskData = task.getName() + "," + task.getDescription() + ",false\n";
                fos.write(taskData.getBytes());
            }

            // Save completed tasks
            for (Task task : completedTasks) {
                String taskData = task.getName() + "," + task.getDescription() + ",true\n";
                fos.write(taskData.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Load tasks from a file
    public void loadTasks() {
        Log.d(TAG,"loadTasks called");
        // Clear existing tasks to avoid duplicates
        todoTasks.clear();
        completedTasks.clear();

        File file = new File(getFilesDir(), FILE_NAME);
        if (!file.exists()) {
            return;  // No file exists, nothing to load
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskData = line.split(",");
                if (taskData.length == 3) {
                    String taskName = taskData[0];
                    String taskDescription = taskData[1];
                    boolean isCompleted = Boolean.parseBoolean(taskData[2]);

                    Task task = new Task(taskName, taskDescription, isCompleted);
                    if (isCompleted) {
                        completedTasks.add(task);
                    } else {
                        todoTasks.add(task);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
