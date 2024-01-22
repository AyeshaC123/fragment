package com.example.fragment;

public class Beacon {
    private String id;
    private String name;
    private String location;
    private String paths;
    private String floor;

    public Beacon(String id, String name, String location, String paths, String floor) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.paths = paths;
        this.floor = floor;
    }

    // Getter and setter methods for each field

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    // You might also include a toString method for easy debugging
    @Override
    public String toString() {
        return "Beacon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", paths='" + paths + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}
