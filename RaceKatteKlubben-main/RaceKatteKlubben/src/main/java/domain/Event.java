package domain;

import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class Event {

    private int id;

    @NotEmpty
    private String eventName;
    private String description;
    private String location;
    private int admin_id;
    private Date date;
    private double price;

    public Event() {}

    public Event(int id, String eventName, String description, String location, int admin_id, Date date, double price) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.admin_id = admin_id;
        this.date = date;
        this.price = price;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getEventName() {return eventName;}
    public void setEventName(String eventName) {this.eventName = eventName;}
    public int getAdmin_id() {return admin_id;}
    public void setAdmin_id(int admin_id) {this.admin_id = admin_id;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                '}';
    }
}
