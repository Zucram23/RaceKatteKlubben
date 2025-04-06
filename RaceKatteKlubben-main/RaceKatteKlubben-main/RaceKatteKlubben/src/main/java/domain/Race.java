package domain;

public class Race {
    private int id;
    private String name;

    public Race(){}

    public Race(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {return id;}
    public String getName() {return name;}
}