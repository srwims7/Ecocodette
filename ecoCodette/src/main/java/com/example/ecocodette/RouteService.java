/*
- contains the method calculateEmissions()
* Formula:co2 emission = distance x emission factor

 */

package com.example.ecocodette;

public class RouteService {

    public double calculateEmissions(double distance, String vehicleType){
        if (vehicleType == null) {
            return distance * 0.3; // fallback for missing input
        }

        String type = vehicleType.toLowerCase();

        if (type.equals("electric")) {
            return distance * 0.05;
        } else if (type.equals("diesel")) {
            return distance * 0.25;
        } else if (type.equals("petrol")) {
            return distance * 0.22;
        } else {
            return distance * 0.3; // fallback for unknown type
        }
    }
}
