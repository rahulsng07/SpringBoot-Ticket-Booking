package com.online_ticket_booking_system.ticket_booking.service;

import com.online_ticket_booking_system.ticket_booking.entity.Route;
import java.util.List;

public interface RouteService {
    List<Route> getAllRoutes();
    Route getRouteById(Long id);
    Route saveRoute(Route route);
    Route updateRoute(Long id, Route updatedRoute);
    void deleteRoute(Long id);
}
