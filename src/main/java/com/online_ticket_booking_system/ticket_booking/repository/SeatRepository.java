package com.online_ticket_booking_system.ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online_ticket_booking_system.ticket_booking.entity.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScheduleId(Long scheduleId);
    long countByScheduleId(Long scheduleId);
}
