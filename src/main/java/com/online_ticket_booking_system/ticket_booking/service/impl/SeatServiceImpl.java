package com.online_ticket_booking_system.ticket_booking.service.impl;

import com.online_ticket_booking_system.ticket_booking.entity.Seat;
import com.online_ticket_booking_system.ticket_booking.repository.SeatRepository;
import com.online_ticket_booking_system.ticket_booking.service.SeatService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSeat(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }
}
