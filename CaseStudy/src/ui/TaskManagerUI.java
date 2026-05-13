package ui;
import data.Task;
import logic.TaskManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceDialog;

import java.util.Optional;

public class TaskManagerUI extends Application {

    private TaskManager tm;
    private ListView<String> taskListView;

    @Override
    public void start(Stage stage) {

    	TextInputDialog login = new TextInputDialog();

    	login.setTitle("Login");
    	login.setHeaderText("Enter User ID");

    	Optional<String> userInput = login.showAndWait();

    	String userID = userInput.orElse("defaultUser");

    	tm = new TaskManager(userID);

        taskListView = new ListView<>();
        refreshTaskList();

        Button addBtn = new Button("Add Task");
        Button updateBtn = new Button("Update Task");
        Button deleteBtn = new Button("Delete Task");
        Button completeBtn = new Button("Mark Complete");
        Button refreshBtn = new Button("View All Tasks");
        Button statusBtn = new Button("View Pending/Completed");
        Button categoryBtn = new Button("View by Category");
        Button saveExitBtn = new Button("Save and Exit");

        // -------- ADD TASK --------
        addBtn.setOnAction(e -> {

            TextInputDialog d1 = new TextInputDialog();
            d1.setHeaderText("Enter Task ID");
            Optional<String> idInput = d1.showAndWait();
            if(idInput.isEmpty()) return;

            TextInputDialog d2 = new TextInputDialog();
            d2.setHeaderText("Enter Task Name");
            Optional<String> nameInput = d2.showAndWait();
            if(nameInput.isEmpty()) return;

            TextInputDialog d3 = new TextInputDialog();
            d3.setHeaderText("Enter Deadline");
            Optional<String> deadlineInput = d3.showAndWait();
            if(deadlineInput.isEmpty()) return;

            TextInputDialog d4 = new TextInputDialog("Pending");
            d4.setHeaderText("Enter Status (Pending/Completed)");
            Optional<String> statusInput = d4.showAndWait();
            if(statusInput.isEmpty()) return;

            TextInputDialog d5 = new TextInputDialog("Personal");
            d5.setHeaderText("Enter Category (Personal/Work/Random)");
            Optional<String> categoryInput = d5.showAndWait();
            if(categoryInput.isEmpty()) return;

            Task t = new Task(
                Integer.parseInt(idInput.get()),
                nameInput.get(),
                deadlineInput.get(),
                statusInput.get(),
                categoryInput.get()
            );

            tm.addTask(t);

            refreshTaskList();

        });

        // -------- DELETE --------
        deleteBtn.setOnAction(e -> {

            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Task ID to delete");

            d.showAndWait().ifPresent(id -> {

                tm.deleteTask(Integer.parseInt(id));

                refreshTaskList();

            });
        });

        // -------- MARK COMPLETE --------
        completeBtn.setOnAction(e -> {

            TextInputDialog d = new TextInputDialog();
            d.setHeaderText("Enter Task ID to mark completed");

            d.showAndWait().ifPresent(id -> {

                tm.markTaskAsComplete(Integer.parseInt(id));

                refreshTaskList();

            });
        });

        // -------- UPDATE --------
        updateBtn.setOnAction(e -> {

            TextInputDialog d1 = new TextInputDialog();
            d1.setHeaderText("Enter Task ID to update");

            Optional<String> idInput = d1.showAndWait();
            if(idInput.isEmpty()) return;

            TextInputDialog d2 = new TextInputDialog();
            d2.setHeaderText("New Task Name");
            Optional<String> nameInput = d2.showAndWait();

            TextInputDialog d3 = new TextInputDialog();
            d3.setHeaderText("New Deadline");
            Optional<String> deadlineInput = d3.showAndWait();

            TextInputDialog d4 = new TextInputDialog("Pending");
            d4.setHeaderText("New Status");
            Optional<String> statusInput = d4.showAndWait();

            TextInputDialog d5 = new TextInputDialog("Personal");
            d5.setHeaderText("New Category");
            Optional<String> categoryInput = d5.showAndWait();

            tm.updateTask(
                Integer.parseInt(idInput.get()),
                nameInput.get(),
                deadlineInput.get(),
                statusInput.get(),
                categoryInput.get()
            );

            refreshTaskList();

        });

        // -------- REFRESH --------
        refreshBtn.setOnAction(e -> refreshTaskList());

        // -------- SAVE EXIT --------
        saveExitBtn.setOnAction(e -> {

            tm.saveTasksToFile();

            stage.close();

        });
     // -------- VIEW PENDING/COMPLETED --------
        statusBtn.setOnAction(e -> {

            ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Pending", "Pending", "Completed");

            dialog.setHeaderText("Choose status");

            dialog.showAndWait().ifPresent(status -> {

                taskListView.getItems().clear();

                for(Task t : tm.getTaskList())
                {
                    if(t.getStatus().equalsIgnoreCase(status))
                    {
                        taskListView.getItems().add(
                            "ID: " + t.getTaskID()
                            + " | " + t.getTaskName()
                            + " | " + t.getDeadline()
                            + " | " + t.getStatus()
                            + " | " + t.getCategory()
                        );
                    }
                }

            });
        });
        
     // -------- VIEW BY CATEGORY --------
        categoryBtn.setOnAction(e -> {

            ChoiceDialog<String> dialog =
                new ChoiceDialog<>("Personal",
                                   "Personal",
                                   "Work",
                                   "Random");

            dialog.setHeaderText("Choose category");

            dialog.showAndWait().ifPresent(category -> {

                taskListView.getItems().clear();

                for(Task t : tm.getTaskList())
                {
                    if(t.getCategory().equalsIgnoreCase(category))
                    {
                        taskListView.getItems().add(
                            "ID: " + t.getTaskID()
                            + " | " + t.getTaskName()
                            + " | " + t.getDeadline()
                            + " | " + t.getStatus()
                            + " | " + t.getCategory()
                        );
                    }
                }

            });

        });

        VBox layout = new VBox(10);

        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
        	    addBtn,
        	    updateBtn,
        	    deleteBtn,
        	    completeBtn,
        	    statusBtn,
        	    refreshBtn,
        	    categoryBtn,
        	    saveExitBtn,
        	    taskListView
        	);

        Scene scene = new Scene(layout,500,600);

        stage.setTitle("Task Manager");

        stage.setScene(scene);

        stage.show();
    }

    private void refreshTaskList()
    {
        taskListView.getItems().clear();

        for(Task t : tm.getTaskList())
        {
            taskListView.getItems().add(
                "ID: " + t.getTaskID()
                + " | " + t.getTaskName()
                + " | " + t.getDeadline()
                + " | " + t.getStatus()
                + " | " + t.getCategory()
            );
        }
    }

    public static void main(String[] args)
    {
        launch();
    }
}