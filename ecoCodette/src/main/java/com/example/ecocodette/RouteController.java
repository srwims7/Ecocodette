/*
- receives requests and uses RouteService to calculate emissions.
* needs to inherit(@autowire) route service
- integrate the API by building the RouteController,
 which is the part that listens for requests and sends back responses.
 */

package com.example.ecocodette;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
//Handles requests for route and emission data.
//DEfines API endpoints that return sample routes for Graingers eco routing system.
@RestController
public class RouteController {
    //Sends back a list of sample routes
    @GetMapping("/routes")
    public List<Route> getRoutes() {
        //DATA COMES FROM API DATABASE, connect with the API
    }


}
