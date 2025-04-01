package org.example.fitnessproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class TableActivityView {


    public static Scene getEmp_ActivitiesTable() {

        //table of employees and their activities

        //table colum for employee id
        TableColumn<Activity, Integer> empIdColumn = new TableColumn<>("Employee Id");
        empIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        empIdColumn.setMaxWidth(50);

        //employee name column
        TableColumn<Activity, String> empNameColumn = new TableColumn<>("Employee Name");
        empNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        empNameColumn.setMinWidth(150);

        TableColumn<Activity, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setMaxWidth(100);

        TableColumn<Activity, Double> durationColumn = new TableColumn<>("Duration in hours");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationColumn.setMinWidth(150);

        TableColumn<Activity, Integer> stepsColumn = new TableColumn<>("Steps (Only in running)");
        stepsColumn.setCellValueFactory(new PropertyValueFactory<>("steps"));
        stepsColumn.setMinWidth(105);

        TableColumn<Activity, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        pointsColumn.setMinWidth(80);


        TableView<Activity> activityTableView = new TableView<>();
        //get the data from the database table
        activityTableView.setItems(MySqlActivityRepository.selectEmployees());
        //add all column to the table
        activityTableView.getColumns().addAll(empIdColumn,empNameColumn,typeColumn,durationColumn,stepsColumn,pointsColumn);
        //adding the add activity feature to the table
        HBox addHbox = getAddActivity(activityTableView);

        // return scene
        VBox layout = new VBox();
        layout.getChildren().addAll(activityTableView,addHbox);
        return new Scene(layout, 600, 350);

    }

    private static HBox getAddActivity(TableView<Activity>Emp_activityTableView)  {

        TextField empIdField= new TextField();
        empIdField.setPromptText("124");
        empIdField.setMaxWidth(50);

        TextField empNameField = new TextField();
        empNameField.setPromptText("George smith");
        empNameField.setMinWidth(60);

        TextField typeField = new TextField();
        typeField.setPromptText("running");
        typeField.setMaxWidth(100);

        TextField durationField = new TextField();
        durationField.setPromptText("1.30");
        durationField.setMaxWidth(80);

        TextField stepsField = new TextField();
        stepsField.setPromptText("2000");
        stepsField.setMaxWidth(60);

        Button add = new Button("Add");
        add.setOnAction(e-> {
            updateTable(Emp_activityTableView, empIdField, empNameField, typeField, durationField, stepsField);
        });

        HBox layout= new HBox();
        layout.getChildren().addAll(empIdField,empNameField,typeField, durationField,stepsField,add);
        layout.setPadding(new Insets(20,20,20,20));
        return layout;
    }

    public static void updateTable(TableView<Activity> activityTableView, TextField empIdField, TextField empNameField,TextField typeField,TextField durationField, TextField stepsField){

        // intializing the data in the hbox
        int id= Integer.parseInt(empIdField.getText());
        String name = empNameField.getText();
        String type = typeField.getText();
        double duration = Double.parseDouble(durationField.getText());
        int steps = Integer.parseInt(stepsField.getText());

        empNameField.clear();
        empIdField.clear();
        typeField.clear();
        durationField.clear();
        stepsField.clear();

        //adding the new data to the table
        Activity newActivity = new Activity(new User(name, id), type, duration, steps,0);
        activityTableView.getItems().add(newActivity);



        //add data into table in db
        MySqlActivityRepository.insertData(id,type,duration,steps, newActivity.getPoints());



    }




}




