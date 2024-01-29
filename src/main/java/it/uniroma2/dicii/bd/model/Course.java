package main.java.it.uniroma2.dicii.bd.model;

import java.sql.Date;

public class Course {
    private String name;
    private double costo;
    private Date startDate;
    private Date endDate;
    private int minSub;
    private int maxSub;
    private int nSub;

    public Course() {
    }

    public Course(String name, double costo, Date startDate, Date endDate, int minSub, int maxSub, int nSub) {
        this.name = name;
        this.costo = costo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minSub = minSub;
        this.maxSub = maxSub;
        this.nSub = nSub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getMinSub() {
        return minSub;
    }

    public void setMinSub(int minSub) {
        this.minSub = minSub;
    }

    public int getMaxSub() {
        return maxSub;
    }

    public void setMaxSub(int maxSub) {
        this.maxSub = maxSub;
    }

    public int getnSub() {
        return nSub;
    }

    public void setnSub(int nSub) {
        this.nSub = nSub;
    }
}
