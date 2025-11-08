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

@RestController
public class RouteController {



}
