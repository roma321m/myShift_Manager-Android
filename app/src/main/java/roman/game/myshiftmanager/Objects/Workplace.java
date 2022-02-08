package roman.game.myshiftmanager.Objects;

public class Workplace {

    public static final String GREEN = "green", BLUE = "blue";
    private String name;
    private String color;
    private double hourlyWage;

    public Workplace() {
    }

    public Workplace(String name, String color, double hourlyWage) {
        setColor(color);
        setHourlyWage(hourlyWage);
        setName(name);
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
        if (color.equals(GREEN) || color.equals(BLUE))
            this.color = color;
        else
            this.color = BLUE;
        return this;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public Workplace setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
        return this;
    }
}
