package com.example.demo.model;

public class UserDetails {

    private int userId;
    private String job;
    private String address;
    private int salary;

    public UserDetails() {

    }

    public UserDetails(int userId, String job, String address, int salary) {
        this.userId = userId;
        this.job = job;
        this.address = address;
        this.salary = salary;
    }

    public int getUserId() {
        return userId;
    }

    public String getJob() {
        return job;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
