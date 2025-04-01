package org.example.fitnessproject;

public class Activity {
    private int id;

    private String type;
    private double duration;
    private int steps;
    private User user;
    private int points;
    private int totalPoints;




    public Activity(User user, String type, double duration, int steps, int points) {

        this.type = type;
        this.duration = duration;
        this.steps = steps;
        this.user = user;
        //calculates points
        this.points=calculatePoint();

    }
    public Activity(User user, String type) {
        this.user = user;
        this.type = type;
    }
    public Activity(User user, int totalPoints) {
        this.user = user;
        this.totalPoints = totalPoints;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return user.getEmpId();
    }

    public String getType() {
        return type;
    }

    public double getDuration() {
        return duration;
    }

    public int getSteps() {
        return steps;
    }

    public String getEmployeeName() {
        return user.getEmpName();
    }
    public User getUser() {
        return user;
    }
    public String getEmpName(){
        return user.getEmpName();
    }
    public int getEmpId(){
        return user.getEmpId();
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getPoints() {

        return points;
    }


    public int calculatePoint(){
        int pointsPerActivity=0;

        switch (type.toLowerCase()){
            case "running":
                pointsPerActivity=steps/10;
                break;
            case "swimming":
                //per hour 2400 since 0.5 -> 1200___
                double pointsDouble = duration*2400;
                pointsPerActivity=(int) pointsDouble;
                break;
            case "workout":
                double pointsDoubleWorkOut= duration*1500;
                pointsPerActivity=(int) pointsDoubleWorkOut;
                break;
            default:
                System.out.println("The exercise is not predefined");
                break;
        }

    return pointsPerActivity;

    }


}

