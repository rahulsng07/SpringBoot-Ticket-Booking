package com.online_ticket_booking_system.ticket_booking.service.impl;

import com.online_ticket_booking_system.ticket_booking.entity.Route;
import com.online_ticket_booking_system.ticket_booking.entity.Schedule;
import com.online_ticket_booking_system.ticket_booking.repository.RouteRepository;
import com.online_ticket_booking_system.ticket_booking.repository.ScheduleRepository;
import com.online_ticket_booking_system.ticket_booking.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        // Fetch route from DB
        Route route = routeRepository.findById(schedule.getRoute().getId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        schedule.setRoute(route);

        // Auto-generate price
        double price = route.getDistance() * 2.5; // You can change multiplier
        schedule.setPrice(price);

        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        Schedule existing = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));

        // Fetch route properly
        Route route = routeRepository.findById(schedule.getRoute().getId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        existing.setRoute(route);
        existing.setDepartureTime(schedule.getDepartureTime());
        existing.setArrivalTime(schedule.getArrivalTime());

        // Recalculate price
        double price = route.getDistance() * 2.5;
        existing.setPrice(price);

        return scheduleRepository.save(existing);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }
}
