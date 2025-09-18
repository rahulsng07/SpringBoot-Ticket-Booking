package com.online_ticket_booking_system.ticket_booking.service;

import com.online_ticket_booking_system.ticket_booking.entity.Seat;
import java.util.List;

public interface SeatService {
    List<Seat> getAllSeats();
    Seat getSeatById(Long id);
    void saveSeat(Seat seat);
    void deleteSeat(Long id);
}
