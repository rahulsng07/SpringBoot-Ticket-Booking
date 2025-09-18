package com.online_ticket_booking_system.ticket_booking.controller;

import com.online_ticket_booking_system.ticket_booking.entity.Schedule;
import com.online_ticket_booking_system.ticket_booking.entity.Seat;
import com.online_ticket_booking_system.ticket_booking.repository.ScheduleRepository;
import com.online_ticket_booking_system.ticket_booking.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;
    private final ScheduleRepository scheduleRepository;

    public SeatController(SeatService seatService, ScheduleRepository scheduleRepository) {
        this.seatService = seatService;
        this.scheduleRepository = scheduleRepository;
    }

    // List all seats
    @GetMapping
    public String listSeats(Model model) {
        model.addAttribute("seats", seatService.getAllSeats());
        return "seats";
    }

    // Show Add Seat Form
    @GetMapping("/add")
    public String addSeatForm(Model model) {
        Seat seat = new Seat();
        seat.setStatus("AVAILABLE");
        seat.setAvailable(true);

        List<Schedule> schedules = scheduleRepository.findAll();
        model.addAttribute("seat", seat);
        model.addAttribute("schedules", schedules);
        return "add-seat";
    }

    // Save Seat (Add or Edit)
    @PostMapping("/save")
    public String saveSeat(@ModelAttribute("seat") Seat seat) {
        Schedule schedule = scheduleRepository.findById(seat.getSchedule().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID"));

        seat.setPrice(schedule.getPrice()); // ✅ fetch price from schedule
        seatService.saveSeat(seat);
        return "redirect:/seats";
    }

    // Edit Seat Form
    @GetMapping("/edit/{id}")
    public String editSeat(@PathVariable Long id, Model model) {
        Seat seat = seatService.getSeatById(id);
        if (seat == null) {
            throw new IllegalArgumentException("Invalid seat ID: " + id);
        }
        model.addAttribute("seat", seat);
        model.addAttribute("schedules", scheduleRepository.findAll());
        return "edit-seat";
    }

    // Delete Seat
    @GetMapping("/delete/{id}")
    public String deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return "redirect:/seats";
    }
}
