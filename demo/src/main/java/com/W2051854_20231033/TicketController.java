package com.W2051854_20231033;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
    @GetMapping("/")
    public String home() {
        return "Welcome to the Ticketing System!";
    }
}


