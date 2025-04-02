package domain;

import java.util.Date;

public class Event {

    private int id;
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
    public String getEventName() {return eventName;}
    public int getAdmin_id() {return admin_id;}
    public String getDescription() {return description;}
    public String getLocation() {return location;}
    public Date getDate() {return date;}
    public double getPrice() {return price;}
}
