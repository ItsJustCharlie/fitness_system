package org.example.fitnessproject;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FilterWithEmpName {
    static Scene getFilterActivity()  {

        TextField empNameField = new TextField();
        empNameField.setPromptText("George smith");
        empNameField.setMinWidth(60);

        TextField typeField = new TextField();
        typeField.setPromptText("running");
        typeField.setMaxWidth(100);

        //table to show the filtered Data

        //employee name column
        TableColumn<Activity, String> empNameColumn = new TableColumn<>("Employee Name");
        empNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        empNameColumn.setMinWidth(200);

        TableColumn<Activity, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setMinWidth(200);







        TableView<Activity> filteredTableView = new TableView<>();
        //get the data from the database table
        filteredTableView.setItems(MySqlActivityRepository.selectEmployees());
        //add all column to the table
        filteredTableView.getColumns().addAll(empNameColumn,typeColumn);
        //adding the add activity feature to the table

        // return scene
        VBox vBox = new VBox();
        vBox.getChildren().addAll(filteredTableView);

        // add the button here , we can generate the table too
        Button filter = new Button("Filter");
        filter.setOnAction(e-> {
            updateFilterTable(filteredTableView,empNameField);
        });

        HBox hBox= new HBox();
        hBox.getChildren().addAll(empNameField, filter);
        hBox.setPadding(new Insets(5,10,10,50));

        // create a boarderpane so the ui looks clean
        BorderPane borderPane= new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(vBox);
        return  new Scene(borderPane,400,350);
    }

    public static void updateFilterTable(TableView<Activity> filteredTableView,  TextField empNameField){

        // intializing the data in the hbox
        String name = empNameField.getText();

        empNameField.clear();

        //adding the new data to the table
        filteredTableView.setItems(MySqlActivityRepository.selectFilteredEmployeesOnlyName(name));


    }


}
