package roman.game.myshiftmanager.Objects;


import java.util.Date;

public class Shift {
    private Date start;
    private Date end;
    private Workplace workplace;
    private double totalTime;

    public Shift(){};

    public Shift(Date start, Date end, Workplace workplace, double totalTime) {
        this.start = start;
        this.end = end;
        this.workplace = workplace;
        this.totalTime = totalTime;
    }

    public Date getStart() {
        return start;
    }

    public Shift setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public Shift setEnd(Date end) {
        this.end = end;
        return this;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public Shift setWorkplace(Workplace workplaceID) {
        this.workplace = workplace;
        return this;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public Shift setTotalTime(double totalTime) {
        this.totalTime = totalTime;
        return this;
    }
}
