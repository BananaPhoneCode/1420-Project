package com.example._1420project;

public class Event {

    public String eventName;
    public String eventCode;
    public String eventDescription;
    public String eventDateNTime;
    public String eventLocation;
    public String eventCost;
    public String eventCapacity;

    public Event(String EC, String EN, String ED, String Loc, String DT, String Cap, String Cost){
        this.eventName = EN;
        this.eventCode = EC;
        this.eventDescription = ED;
        this.eventLocation = Loc;
        this.eventDateNTime = DT;
        this.eventCapacity = Cap;
        this.eventCost = Cost;
    }

    // Getters
    public final String getEventName(){return eventName; }
    public final String getEventCode(){return eventCode; }
    public final String getEventDescription(){return eventDescription; }
    public final String getEventDateNTime(){return eventDateNTime; }
    public final String getEventLocation(){return eventLocation; }
    public final String getEventCost(){return eventCost; }
    public final String getEventCapacity(){return eventCapacity; }

    // Setters
    public void setEventName(String eventName) {this.eventName = eventName; }
    public void setEventCode(String eventCode) {this.eventCode = eventCode;}
    public void setEventDescription(String eventDescription) {this.eventDescription = eventDescription;}
    public void setEventDateNTime(String eventDateTime) {this.eventDateNTime = eventDateTime;}
    public void setEventLocation(String eventLocation) {this.eventLocation = eventLocation;}
    public void setEventCost(String eventCost) {this.eventCost = eventCost;}
    public void setEventCapacity(String eventCapacity) {this.eventCapacity = eventCapacity;}

}
