package seedu.healthbud.log;

public class Meal extends Log {

    private String name;
    private String calories;
    private String time;

    public Meal(String name, String calories, String date, String time){
        super(date, "meal");
        assert name != null : "Meal name should not be null";
        assert calories != null : "Calories should not be null";
        assert time != null : "Time should not be null";
        this.name = name;
        this.calories = calories;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String  getCalories() {
        return calories;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%s (%s cal) on: %s at: %s", name, calories, getDate(), time);
    }
}
