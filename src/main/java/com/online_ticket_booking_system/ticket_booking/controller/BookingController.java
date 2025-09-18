package com.online_ticket_booking_system.ticket_booking.controller;

import com.online_ticket_booking_system.ticket_booking.entity.Booking;
import com.online_ticket_booking_system.ticket_booking.service.BookingService;
import com.online_ticket_booking_system.ticket_booking.service.SeatService;
import com.online_ticket_booking_system.ticket_booking.service.ScheduleService;
import com.online_ticket_booking_system.ticket_booking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final SeatService seatService;

    public BookingController(BookingService bookingService, UserService userService,
                             ScheduleService scheduleService, SeatService seatService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.scheduleService = scheduleService;
        this.seatService = seatService;
    }

    @GetMapping
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings";
    }

    @GetMapping("/add")
    public String addBookingForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        model.addAttribute("seats", seatService.getAllSeats());
        return "add-booking";
    }

    @PostMapping("/create")
    public String createBooking(@RequestParam Long userId,
                                @RequestParam Long scheduleId,
                                @RequestParam Long seatId,
                                @RequestParam String paymentStatus) {

        Booking booking = new Booking();
        booking.setUser(userService.getUserById(userId));
        booking.setSchedule(scheduleService.getScheduleById(scheduleId));
        booking.setSeat(seatService.getSeatById(seatId));

        // ✅ Set price directly from schedule
        booking.setPrice(booking.getSchedule().getPrice());

        booking.setPaymentStatus(paymentStatus);
        booking.setStatus("PENDING");
        booking.setBookingTime(LocalDateTime.now());

        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String editBookingForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        model.addAttribute("seats", seatService.getAllSeats());
        return "edit-booking";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable Long id,
                                @RequestParam Long userId,
                                @RequestParam Long scheduleId,
                                @RequestParam Long seatId,
                                @RequestParam String status,
                                @RequestParam String paymentStatus) {

        Booking booking = bookingService.getBookingById(id);
        booking.setUser(userService.getUserById(userId));
        booking.setSchedule(scheduleService.getScheduleById(scheduleId));
        booking.setSeat(seatService.getSeatById(seatId));

        // ✅ Always update price from schedule
        booking.setPrice(booking.getSchedule().getPrice());

        booking.setStatus(status);
        booking.setPaymentStatus(paymentStatus);

        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @PostMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        booking.setStatus("CANCELLED");
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }
    
 // Delete Booking
    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return "redirect:/bookings";
    }

}
