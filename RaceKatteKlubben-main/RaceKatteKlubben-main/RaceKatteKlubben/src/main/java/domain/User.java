package domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class User {
    private int id;
    private List<Cat> cats;

    @NotEmpty
    @Size(min = 2, max = 50, message = "Name must be at least 2 characters long")
    private String name;

    @NotEmpty
    @Size(min = 10, max = 150)
    private String email;

    @NotEmpty
    @Size(min = 5, max = 50, message = "Password must be at least 5 characters long")
    private String password;

    public User() {}

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public List<Cat> getCats() {return cats;}
    public void setCats(List<Cat> cats) {this.cats = cats;}

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password;
    }
}
