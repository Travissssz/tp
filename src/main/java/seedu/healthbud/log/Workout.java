package seedu.healthbud.log;


public class Workout extends Log {

    private String name;
    private String reps;
    private String sets;
    private String weight;

    public Workout(String name, String reps, String sets, String date, String weight) {
        super(date, "workout");

        assert name != null : "Workout name should not be null";
        assert reps != null : "Reps should not be null";
        assert sets != null : "Sets should not be null";
        assert weight != null : "Weight should not be null";

        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getReps() {
        return reps;
    }

    public String getSets() {
        return sets;
    }

    public String getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s (%s sets of %s kg for %s reps) on %s", name, sets,weight ,reps, getDate());
    }
}
