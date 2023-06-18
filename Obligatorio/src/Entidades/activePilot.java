package Entidades;

public class activePilot implements Comparable<activePilot> {
    private String name;

    private int numberOfOccurences;

    @Override
    public int compareTo(activePilot other) {
        return Integer.compare(this.numberOfOccurences, other.numberOfOccurences);
    }


    public activePilot(String name) {
        this.name = name;
        this.numberOfOccurences = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfOccurences() {
        return numberOfOccurences;
    }

    public void setNumberOfOccurences(int numberOfOccurences) {
        this.numberOfOccurences = numberOfOccurences;
    }
}
