package org.example.fitnessproject;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewLeaderBoard {
    public static Scene getLeaderBoardWindow(){



        TableColumn<Activity, Integer> empIdColumn = new TableColumn<>("Employee Id");
        empIdColumn.setCellValueFactory(new PropertyValueFactory<>("empId"));
        empIdColumn.setMaxWidth(100);

        //employee name column
        TableColumn<Activity, String> empNameColumn = new TableColumn<>("Employee Name");
        empNameColumn.setCellValueFactory(new PropertyValueFactory<>("empName"));
        empNameColumn.setMinWidth(200);

        TableColumn<Activity, Integer> totalpointsColumn = new TableColumn<>("Total Points");
        totalpointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));
        totalpointsColumn.setMaxWidth(100);

        TableView<Activity> leaderTableView = new TableView<>();
        //get the data from the database table
        leaderTableView.setItems(MySqlActivityRepository.selectToLeaderBoard());
        //add all column to the table
        leaderTableView.getColumns().addAll(empIdColumn,empNameColumn,totalpointsColumn);
        //adding the add activity feature to the table


        VBox layout = new VBox();
        layout.getChildren().addAll(leaderTableView);
        return new Scene(layout, 400, 350);

    }

}
