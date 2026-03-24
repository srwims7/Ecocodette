package com.example.ecocodette;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Route {
    @NotBlank(message = "Origin is required")
    private String origin;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    private double co2Emissions;

    @Min(value = 0, message = "Distance must be zero or greater")
    private double distance;

    public Route() {
    }

    public Route(String origin, String destination, String vehicleType, double co2Emissions, double distance) {
        this.setOrigin(origin);
        this.setDestination(destination);
        this.setVehicleType(vehicleType);
        this.setCo2Emissions(co2Emissions);
        this.setDistance(distance);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(double co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return " Route: " + origin + " to " + destination + "\n" +
                " Using: " + vehicleType + "\n" +
                " Distance: " + distance + "\n" +
                " CO₂ emissions: " + co2Emissions + " kg";

    }
}
