package domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Cat {

    private int id;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;

    @NotEmpty
    private int age;

    @NotEmpty
    private User owner;

    @NotEmpty
    private Race race;

    public Cat() {}

    public Cat(int id, String name, int age, User owner, Race race){
        this.id = id;
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.race = race;
    }


    public int getId(){return id;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public User getOwner(){return owner;}
    public Race getRace(){return race;}

    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAge(int age) {this.age = age;}
    public void setOwner(User owner) {this.owner = owner;}
    public void setRace(Race race) {this.race = race;}
}

