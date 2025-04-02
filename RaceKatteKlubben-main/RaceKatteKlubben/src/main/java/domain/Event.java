package domain;

import java.util.Date;

public class Event {

    private int id;
    private String eventName;
    private String description;
    private String location;
    private Date date;
    private double price;

    public Event(int id, String eventName, String description, String location, Date date, double price) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.date = date;
        this.price = price;
    }
    public int getId() {return id;}
    public String getEventName() {return eventName;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public Date getDate() {return date;}
    public double getPrice() {return price;}
}
