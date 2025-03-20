package com.example._1420project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private final StringProperty eventName;
    private final StringProperty eventDate;
    private final StringProperty location;

    public Event(String name, String date, String location) {
        this.eventName = new SimpleStringProperty(name);
        this.eventDate = new SimpleStringProperty(date);
        this.location = new SimpleStringProperty(location);
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public StringProperty eventDateProperty() {
        return eventDate;
    }

    public StringProperty locationProperty() {
        return location;
    }
}
