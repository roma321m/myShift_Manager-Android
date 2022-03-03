package roman.game.myshiftmanager.Objects;

import java.io.Serializable; // only serializable obj can be sent in intent
import java.time.Duration;
import java.util.UUID;

public class Shift implements Serializable {

    private String id;
    private int startYear, startMonth, startDayOfMonth, startHour, startMinutes;
    private int endYear, endMonth, endDayOfMonth, endHour, endMinutes;
    private String workplaceID;
    private double totalTime;
    private double revenue;

    public Shift() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Shift setId() {
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public Shift setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public Shift setStartMonth(int startMonth) {
        this.startMonth = startMonth;
        return this;
    }

    public int getStartDayOfMonth() {
        return startDayOfMonth;
    }

    public Shift setStartDayOfMonth(int startDayOfMonth) {
        this.startDayOfMonth = startDayOfMonth;
        return this;
    }

    public int getStartHour() {
        return startHour;
    }

    public Shift setStartHour(int startHour) {
        this.startHour = startHour;
        return this;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public Shift setStartMinutes(int startMinutes) {
        this.startMinutes = startMinutes;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public Shift setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public Shift setEndMonth(int endMonth) {
        this.endMonth = endMonth;
        return this;
    }

    public int getEndDayOfMonth() {
        return endDayOfMonth;
    }

    public Shift setEndDayOfMonth(int endDayOfMonth) {
        this.endDayOfMonth = endDayOfMonth;
        return this;
    }

    public int getEndHour() {
        return endHour;
    }

    public Shift setEndHour(int endHour) {
        this.endHour = endHour;
        return this;
    }

    public int getEndMinutes() {
        return endMinutes;
    }

    public Shift setEndMinutes(int endMinutes) {
        this.endMinutes = endMinutes;
        return this;
    }

    public Shift setWorkplaceID(String workplaceID) {
        this.workplaceID = workplaceID;
        return this;
    }

    public String getWorkplaceID() {
        return workplaceID;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public Shift setTotalTime(double totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public double getRevenue() {
        return revenue;
    }

    public Shift setRevenue(double revenue) {
        this.revenue = revenue;
        return this;
    }

    public static String getTimeInHHMM (long milliseconds){
        Duration duration = Duration.ofMillis(milliseconds);
        long seconds = duration.getSeconds();
        long HH = seconds / 3600;
        long MM = (seconds % 3600) / 60;
        return String.format("%02d:%02d", HH, MM);
    }

    public void setStart(int[] from) {
        setStartYear(from[0]);
        setStartMonth(from[1]);
        setStartDayOfMonth(from[2]);
        setStartHour(from[3]);
        setStartMinutes(from[4]);
    }

    public void setEnd(int[] to) {
        setEndYear(to[0]);
        setEndMonth(to[1]);
        setEndDayOfMonth(to[2]);
        setEndHour(to[3]);
        setEndMinutes(to[4]);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id='" + id + '\'' +
                '}';
    }
}
