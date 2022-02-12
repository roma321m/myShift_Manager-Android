package roman.game.myshiftmanager.Objects;

import java.io.Serializable;
import java.time.Duration;

public class Shift implements Serializable {

    private String start;
    private String end;
    private String workplaceID;
    private double totalTime;
    private double revenue;

    public Shift() {
    }

    public String getStart() {
        return start;
    }

    public Shift setStart(String start) {
        this.start = start;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public Shift setEnd(String end) {
        this.end = end;
        return this;
    }

    public Shift setWorkplaceID(String workplaceID) {
        this.workplaceID = workplaceID;
        return this;
    }

    public String getWorkplaceID() {
        return workplaceID;
    }

    public Shift setWorkplaceID(int workplaceID) {
        this.workplaceID = workplaceID + "";
        return this;
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

}
