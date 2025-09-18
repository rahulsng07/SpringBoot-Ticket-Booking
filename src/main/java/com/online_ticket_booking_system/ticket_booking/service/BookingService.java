package com.online_ticket_booking_system.ticket_booking.service;

import com.online_ticket_booking_system.ticket_booking.entity.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    Booking saveBooking(Booking booking);

    Booking updateBooking(Booking booking);  // Make sure this exists

    void deleteBookingById(Long id);  // Make sure this exists
}
