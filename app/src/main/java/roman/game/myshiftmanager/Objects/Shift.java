package roman.game.myshiftmanager.Objects;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Shift {

    private Date start;
    private Date end;
    private Workplace workplace;
    private double totalTime; // in hours
    private double revenue;

    public Shift() {
    }

    ;

    public Shift(Date start, Date end, Workplace workplace) {
        setStart(start);
        setEnd(end);
        setWorkplace(workplace);
        setTotalTime();
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

    public Shift setTotalTime() {
        if (start == null || end == null)
            return this;
        long diffInMillies = Math.abs(end.getTime() - start.getTime());
        this.totalTime = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return this;
    }

    public double getRevenue() {
        return revenue;
    }

    public Shift setRevenue() {
        if (workplace == null)
            return this;
        this.revenue = totalTime * workplace.getHourlyWage();
        return this;
    }
}
