package com.online_ticket_booking_system.ticket_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder; // MISSING IMPORT
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer; // MISSING IMPORT

@SpringBootApplication
public class TicketBookingApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TicketBookingApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApplication.class, args);
    }
}
