package roman.game.myshiftmanager.Objects;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int currency;
    private int timeFormat;
    private int dateFormat;

    public User(){}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
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
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getCurrency() {
        return currency;
    }

    public User setCurrency(int currency) {
        this.currency = currency;
        return this;
    }

    public int getTimeFormat() {
        return timeFormat;
    }

    public User setTimeFormat(int timeFormat) {
        this.timeFormat = timeFormat;
        return this;
    }

    public int getDateFormat() {
        return dateFormat;
    }

    public User setDateFormat(int dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }
}
