package com.example.ecocodette;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping({"/routes", "/api/routes"})
@CrossOrigin(origins = "*")
public class RouteController {

    private final RouteService routeService;
    private final List<Route> routes = new CopyOnWriteArrayList<>();

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public Route createRoute(@Valid @RequestBody Route route) {
        double emissions = routeService.calculateEmissions(route.getDistance(), route.getVehicleType());
        route.setCo2Emissions(emissions);
        routes.add(route);
        return route;
    }

    @GetMapping
    public List<Route> getRoutes() {
        return routes;
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working!";
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        double totalEmissions = routes.stream()
                .mapToDouble(Route::getCo2Emissions)
                .sum();

        Set<String> suggestions = new LinkedHashSet<>();

        if (routes.isEmpty()) {
            suggestions.add("Add routes to calculate CO₂ impact and get tailored suggestions.");
        }

        if (routes.stream().anyMatch(route -> !"electric".equalsIgnoreCase(route.getVehicleType()))) {
            suggestions.add("Consider electric transport for shorter trips to reduce emissions.");
        }

        if (routes.stream().anyMatch(route -> route.getDistance() < 5)) {
            suggestions.add("For short trips under 5 km, walking or biking can eliminate emissions.");
        }

        if (totalEmissions > 20) {
            suggestions.add("Total emissions are high; combine errands or use public transit where possible.");
        }

        if (suggestions.isEmpty()) {
            suggestions.add("Great job! Your current route choices are already low-emission.");
        }

        return Map.of(
                "totalEmissionsKg", totalEmissions,
                "suggestions", new ArrayList<>(suggestions),
                "routesCount", routes.size()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", "Invalid route payload",
                        "errors", errors
                ));
    }
}
