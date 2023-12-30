package ba.ssst.edu;

import java.util.ArrayList;

public class Constraints {
    private final char source;
    private final char destination;
    private final String constraint;
    private final Double probability;

    private static final ArrayList<Constraints> constraintsList = new ArrayList<>();

    public Constraints(char source, char destination, String constraint, Double probability) {
        this.source = source;
        this.destination = destination;
        this.constraint = constraint;
        this.probability = probability;
        constraintsList.add(this);
    }

    public char getSource() {
        return source;
    }

    public char getDestination() {
        return destination;
    }

    public String getConstraint() {
        return constraint;
    }

    public Double getProbability() {
        return probability;
    }

    public static ArrayList<Constraints> getConstraints() {
        return constraintsList;
    }
}
