package com.online_ticket_booking_system.ticket_booking.controller;

import com.online_ticket_booking_system.ticket_booking.entity.Schedule;
import com.online_ticket_booking_system.ticket_booking.service.ScheduleService;
import com.online_ticket_booking_system.ticket_booking.repository.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private RouteRepository routeRepository;

    // Show all schedules
    @GetMapping
    public String listSchedules(Model model) {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        model.addAttribute("schedules", schedules);
        return "schedules";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("routes", routeRepository.findAll());
        return "add-schedule";
    }

    // Save new schedule
    @PostMapping("/add")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        scheduleService.saveSchedule(schedule);
        return "redirect:/schedules";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("routes", routeRepository.findAll());
        return "edit-schedule";
    }

    // Update schedule
    @PostMapping("/update/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute("schedule") Schedule schedule) {
        scheduleService.updateSchedule(id, schedule);
        return "redirect:/schedules";
    }

    // Delete schedule
    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedules";
    }
}
