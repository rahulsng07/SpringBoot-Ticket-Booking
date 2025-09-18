package com.online_ticket_booking_system.ticket_booking.repository;

import com.online_ticket_booking_system.ticket_booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
