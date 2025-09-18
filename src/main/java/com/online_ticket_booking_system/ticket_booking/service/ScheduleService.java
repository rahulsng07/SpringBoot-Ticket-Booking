package com.online_ticket_booking_system.ticket_booking.service;

import com.online_ticket_booking_system.ticket_booking.entity.Schedule;
import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    Schedule getScheduleById(Long id);
    Schedule saveSchedule(Schedule schedule);
    Schedule updateSchedule(Long id, Schedule schedule);   
    void deleteSchedule(Long id);
}
