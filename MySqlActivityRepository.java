package org.example.fitnessproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.sql.*;
import java.util.ArrayList;

public class MySqlActivityRepository {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/abc_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Sam123";

    public static void insertData( int empId, String typeField, double duration, int steps, int points) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            String type= typeField.toLowerCase();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO employee_activities (Emp_Id, type, Duration, Steps, points) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, empId);
            preparedStatement.setString(2, type);
            preparedStatement.setDouble(3, duration);
            preparedStatement.setInt(4, steps);
            preparedStatement.setInt(5,points);

            // checks the updated rows
            int effectedRows = preparedStatement.executeUpdate();

            //if it is bigger than 0 return a message
            if (effectedRows > 0) {
                System.out.println("Data insert is successful");
            }
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static ObservableList<Activity> selectEmployees() {
        // create an empty list to add  the already entered data
        ObservableList<Activity> employeesWithActivities = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully!");


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT E.Emp_Id, E.Emp_Name, A.type, A.Duration, A.Steps, A.points FROM employee E RIGHT JOIN employee_activities A ON E.Emp_Id=A.Emp_Id" );


            while (resultSet.next()) {

                int id = resultSet.getInt("Emp_Id");
                String name = resultSet.getString("Emp_Name");
                String type = resultSet.getString("type");
                Double duration = resultSet.getDouble("Duration");
                int steps = resultSet.getInt("Steps");
                int points= resultSet.getInt("points");

                employeesWithActivities.addAll(
                      new Activity(new User(name,id),type,duration,steps, points)
                );

                    System.out.printf("View activities");

            }
            resultSet.close();
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesWithActivities;
    }

    public static ObservableList<Activity> selectToLeaderBoard() {
        // create an empty list to add  the already entered data
        ObservableList<Activity> leaderBoard = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully!");


            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select E.Emp_id, E.Emp_Name, SUM(points) AS total_points From employee E RIGHT JOIN employee_activities A ON E.Emp_Id=A.Emp_Id GROUP BY Emp_id ORDER BY total_points DESC;" );


            while (resultSet.next()) {

                int id = resultSet.getInt("Emp_Id");
                String name = resultSet.getString("Emp_Name");
                int totalPoints = resultSet.getInt("total_points");


                leaderBoard.addAll(
                     new Activity(new User(name,id),totalPoints)
                );

                System.out.printf("View activities");

            }
            resultSet.close();
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderBoard;
    }
    public static ObservableList<Activity> selectFilteredEmployees(String empNameField, String typeField) {
        // create an empty list to add  the filtered employees
        ObservableList<Activity> filteredEmployees = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully!");

            String empName=empNameField.toLowerCase();
            String type=typeField.toLowerCase();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT E.Emp_Name, A.type FROM employee E RIGHT JOIN employee_activities A ON E.Emp_Id=A.Emp_Id Where E.Emp_Name LIKE ? AND A.type LIKE ? ");
            preparedStatement.setString(1, empName);
            preparedStatement.setString(2, type);
            //add the executed query data to a resultset
            ResultSet resultSet = preparedStatement.executeQuery( );



            while (resultSet.next()) {

                //get the data in the resultset
                String name = resultSet.getString("Emp_Name");
                String addType = resultSet.getString("type");
                // since the id is not needed just write 0
                //add the data as a new activity and to the observable list
                filteredEmployees.addAll(
                        new Activity(new User(name,0),addType)
                );

            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredEmployees;
    }
    public static ObservableList<Activity> selectFilteredEmployeesOnlyName(String empNameField) {
        // create an empty list to add  the filtered employees
        ObservableList<Activity> filteredEmployees = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully!");

            String empName=empNameField.toLowerCase();


            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT E.Emp_Name, A.type FROM employee E RIGHT JOIN employee_activities A ON E.Emp_Id=A.Emp_Id Where E.Emp_Name LIKE ?  ");
            preparedStatement.setString(1, empName);

            //add the executed query data to a resultset
            ResultSet resultSet = preparedStatement.executeQuery( );

            while (resultSet.next()) {

                //get the data in the resultset
                String name = resultSet.getString("Emp_Name");
                String addType = resultSet.getString("type");
                // since the id is not needed just write 0
                //add the data as a new activity and to the observable list
                filteredEmployees.addAll(
                        new Activity(new User(name,0),addType)
                );
            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredEmployees;
    }
    public static ObservableList<Activity> selectFilteredEmployeesOnlyType(String typeField) {
        // create an empty list to add  the filtered employees
        ObservableList<Activity> filteredEmployees = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database successfully!");

            String type=typeField.toLowerCase();


            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT E.Emp_Name, A.type FROM employee E RIGHT JOIN employee_activities A ON E.Emp_Id=A.Emp_Id Where A.type LIKE ?  ");
            preparedStatement.setString(1, type);

            //add the executed query data to a resultset
            ResultSet resultSet = preparedStatement.executeQuery( );



            while (resultSet.next()) {

                //get the data in the resultset
                String name = resultSet.getString("Emp_Name");
                String addType = resultSet.getString("type");
                // since the id is not needed just write 0
                //add the data as a new activity and to the observable list
                filteredEmployees.addAll(
                        new Activity(new User(name,0),addType)
                );

            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredEmployees;
    }




}
