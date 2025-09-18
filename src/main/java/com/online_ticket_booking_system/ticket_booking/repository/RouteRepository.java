package com.online_ticket_booking_system.ticket_booking.repository;

import com.online_ticket_booking_system.ticket_booking.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
