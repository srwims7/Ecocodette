/*
Handles API Request
- receives requests and uses RouteService to calculate emissions.
* needs to inherit(@autowire) route service
- integrate the API by building the RouteController,
 which is the part that listens for requests and sends back responses.
 */

package com.example.ecocodette;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired// Collects the data from routeServices
    private RouteService routeService;

    //An arraylist that will store route memory
    private List<Route> routes = new ArrayList<>();

    // Receives  the route data , calculates the route and returns the updated route
    @PostMapping
    public Route createRoute(@RequestBody Route route) { // used to accept JSON
        double emissions = routeService.calculateEmissions(route.getDistance(),route.getVehicleType());
        route.setCo2Emissions(emissions);
        routes.add(route);
        return route;
    }

    // returns all saved routes
    @GetMapping
    public List<Route> getRoutes(){
        return routes;
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working!";
    }
}

