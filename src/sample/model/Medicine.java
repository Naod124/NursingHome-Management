package sample.model;

import java.sql.Time;

public class Medicine {
    private String name ;
    private int quantity;
    private Time duration ;

    public Medicine(String name, int quantity, Time duration) {
        this.name = name;
        this.quantity = quantity;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
