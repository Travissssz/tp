package seedu.healthbud.log;

public class PersonalBest extends Log {

    private String exercise;
    private String weight;

    public PersonalBest(String exercise, String weight, String date){
        super(date, "pb");

        assert exercise != null : "Exercise should not be null";
        assert weight != null : "Weight should not be null";

        this.exercise = exercise;
        this.weight = weight;
    }

    public String getExercise() {
        return exercise;
    }

    public String getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format(" %s %s kg on (%s)", exercise, weight, getDate());
    }
}
