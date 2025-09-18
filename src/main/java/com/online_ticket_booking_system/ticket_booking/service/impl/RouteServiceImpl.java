package com.online_ticket_booking_system.ticket_booking.service.impl;

import com.online_ticket_booking_system.ticket_booking.entity.Route;
import com.online_ticket_booking_system.ticket_booking.repository.RouteRepository;
import com.online_ticket_booking_system.ticket_booking.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(Long id) {
        return routeRepository.findById(id).orElse(null);
    }

    @Override
    public Route saveRoute(Route route) {
        // Always auto-generate distance & duration for new routes
        route.setDistance(calculateDistance(route.getFrom(), route.getDestination()));
        route.setDuration(calculateDuration(route.getDistance()));
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Long id, Route updatedRoute) {
        Route existingRoute = getRouteById(id);
        if (existingRoute != null) {
            boolean changed = false;

            if (!existingRoute.getFrom().equals(updatedRoute.getFrom())) {
                existingRoute.setFrom(updatedRoute.getFrom());
                changed = true;
            }
            if (!existingRoute.getDestination().equals(updatedRoute.getDestination())) {
                existingRoute.setDestination(updatedRoute.getDestination());
                changed = true;
            }

            // Recalculate only if "from" or "destination" changed
            if (changed) {
                existingRoute.setDistance(calculateDistance(existingRoute.getFrom(), existingRoute.getDestination()));
                existingRoute.setDuration(calculateDuration(existingRoute.getDistance()));
            }

            return routeRepository.save(existingRoute);
        }
        return null;
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    // Mock logic for auto-generation
    private double calculateDistance(String from, String destination) {
        // Example: random distance until we connect with real map API
        return Math.round((50 + Math.random() * 400) * 10.0) / 10.0;
    }

    private String calculateDuration(double distance) {
        int minutes = (int) (distance / 0.8); // avg bus speed ~48 km/h
        int hours = minutes / 60;
        int mins = minutes % 60;
        return hours + "h " + mins + "m";
    }
}
