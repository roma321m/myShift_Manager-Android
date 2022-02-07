package roman.game.myshiftmanager.Objects;

import java.util.ArrayList;

public class User {
    private String worksID;
    private String shiftsID;
    private String userID;

    private String firstName;
    private String LastName;
    private ArrayList<Workplace> works;
    private ArrayList<Shift> shifts;

    public User(){}

    public User(String id, String fName, String lName){
        setUserID(id);
        setFirstName(fName);
        setLastName(lName);
    }

    public String getWorksID() {
        return worksID;
    }

    public User setWorksID(String worksID) {
        this.worksID = worksID;
        return this;
    }

    public String getShiftsID() {
        return shiftsID;
    }

    public User setShiftsID(String shiftsID) {
        this.shiftsID = shiftsID;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public User setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return LastName;
    }

    public User setLastName(String lastName) {
        LastName = lastName;
        return this;
    }

    public ArrayList<Workplace> getWorks() {
        return works;
    }

    public User setWorks(ArrayList<Workplace> works) {
        this.works = works;
        return this;
    }

    public ArrayList<Shift> getShifts() {
        return shifts;
    }

    public User setShifts(ArrayList<Shift> shifts) {
        this.shifts = shifts;
        return this;
    }
}
