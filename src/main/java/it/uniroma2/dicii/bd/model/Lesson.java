package main.java.it.uniroma2.dicii.bd.model;

import java.sql.Time;

public class Lesson {
    private String commaDays;
    private Time startAt;
    private Time endAt;
    private String tub;
    private String course;

    public Lesson() {
    }

    public String getCommaDays() {
        return commaDays;
    }

    public void setCommaDays(String commaDays) {
        this.commaDays = commaDays;
    }

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    public Time getEndAt() {
        return endAt;
    }

    public void setEndAt(Time endAt) {
        this.endAt = endAt;
    }

    public String getTub() {
        return tub;
    }

    public void setTub(String tub) {
        this.tub = tub;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
