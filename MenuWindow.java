package org.example.fitnessproject;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuWindow  {
    //set primary stage to return the menu
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }


    public static Scene getWindow(){


        Menu menu= new Menu("_Activities");

        // creating the menu item add activity
        ToggleGroup themes= new ToggleGroup();
        RadioMenuItem theme1 = new RadioMenuItem("op 1");
        MenuItem addActivity= new MenuItem("View and Add Activity");
        addActivity.setOnAction(e->{
            System.out.println("view activity");
            // setting
            primaryStage.setTitle("Activity Window!");
            Scene scene= TableActivityView.getEmp_ActivitiesTable();
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        menu.getItems().add(addActivity);




        Menu check= new Menu("_Check");
        //filter employees using name and type
        MenuItem filterWNameAndType= new MenuItem("Filter with Employee Name and type");
        filterWNameAndType.setOnAction(e->{
            System.out.println("Filter with Employee Name and type");
            primaryStage.setTitle("Filter With Name and Type!");
            Scene scene= FilterWithEmpAndTypeView.getFilterActivity();
            primaryStage.setScene(scene);
            primaryStage.show();
           
                });
        check.getItems().add(filterWNameAndType);

        // filter with only employee name
        MenuItem filterOnlyName= new MenuItem("Filter With Only Employee name");
        filterOnlyName.setOnAction(e->{
            System.out.println("Filter With Only Employee name");
            primaryStage.setTitle("Filter With Name!");
            Scene scene= FilterWithEmpName.getFilterActivity();
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        check.getItems().add(filterOnlyName);

        // filter with only activity type
        MenuItem filterOnlyType= new MenuItem("Filter With Only activity type");
        filterOnlyType.setOnAction(e->{
            System.out.println("Filter With Only activity type");
            primaryStage.setTitle("Filter With Type!");
            Scene scene= FilterWithType.getFilterActivity();
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        check.getItems().add(filterOnlyType);







        MenuItem leaderBoard= new MenuItem("View Leader Board");
        leaderBoard.setOnAction(e-> {
            System.out.println("checking leader board");
            primaryStage.setTitle("Leader Board!");
            Scene scene= ViewLeaderBoard.getLeaderBoardWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        check.getItems().add(leaderBoard);

        MenuBar menuBar= new MenuBar();
        menuBar.getMenus().addAll(menu,check);


        BorderPane layout= new BorderPane();
        layout.setTop(menuBar);
        return new Scene(layout, 600, 350);
    }
}
