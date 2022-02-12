package roman.game.myshiftmanager.Objects;

import java.io.Serializable;

public class Workplace implements Serializable {

    private String name, color;
    private int breakTimeUnpaid;
    private double hourlyWage, vacationPayments, deductionPerShift, bonusesPerShift, dailyTravelExpenses, monthlyTravelExpenses;

    public Workplace() {
    }

    public int getBreakTimeUnpaid() {
        return breakTimeUnpaid;
    }

    public Workplace setBreakTimeUnpaid(int breakTimeUnpaid) {
        this.breakTimeUnpaid = breakTimeUnpaid;
        return this;
    }

    public double getVacationPayments() {
        return vacationPayments;
    }

    public Workplace setVacationPayments(double vacationPayments) {
        this.vacationPayments = vacationPayments;
        return this;
    }

    public double getDeductionPerShift() {
        return deductionPerShift;
    }

    public Workplace setDeductionPerShift(double deductionPerShift) {
        this.deductionPerShift = deductionPerShift;
        return this;
    }

    public double getBonusesPerShift() {
        return bonusesPerShift;
    }

    public Workplace setBonusesPerShift(double bonusesPerShift) {
        this.bonusesPerShift = bonusesPerShift;
        return this;
    }

    public double getDailyTravelExpenses() {
        return dailyTravelExpenses;
    }

    public Workplace setDailyTravelExpenses(double dailyTravelExpenses) {
        this.dailyTravelExpenses = dailyTravelExpenses;
        return this;
    }

    public double getMonthlyTravelExpenses() {
        return monthlyTravelExpenses;
    }

    public Workplace setMonthlyTravelExpenses(double monthlyTravelExpenses) {
        this.monthlyTravelExpenses = monthlyTravelExpenses;
        return this;
    }

    public String getName() {
        return name;
    }

    public Workplace setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Workplace setColor(String color) {
        this.color = color;
        return this;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public Workplace setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
        return this;
    }

    @Override
    public String toString() {
        return "Workplace{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", breakTimeUnpaid=" + breakTimeUnpaid +
                ", hourlyWage=" + hourlyWage +
                ", vacationPayments=" + vacationPayments +
                ", deductionPerShift=" + deductionPerShift +
                ", bonusesPerShift=" + bonusesPerShift +
                ", dailyTravelExpenses=" + dailyTravelExpenses +
                ", monthlyTravelExpenses=" + monthlyTravelExpenses +
                '}';
    }

    public double getRevenue(double totalTime) {
        double revenue = 0;

        totalTime = totalTime - (breakTimeUnpaid/60);
        revenue += hourlyWage*totalTime;
        revenue += bonusesPerShift + dailyTravelExpenses;
        revenue -= deductionPerShift;
        return revenue;
    }

    public double getMonthlyRevenue(){
        return vacationPayments + monthlyTravelExpenses;
    }
}
