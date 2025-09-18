CREATE DATABASE IF NOT EXISTS ticket_booking_system;
USE ticket_booking_system;

-- USERS TABLE
CREATE TABLE IF NOT EXISTS users (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    role VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ROUTES TABLE
CREATE TABLE IF NOT EXISTS routes (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `from` VARCHAR(255),
    destination VARCHAR(255),
    distance DOUBLE NOT NULL,
    duration VARCHAR(255)
);

-- SCHEDULES TABLE
CREATE TABLE IF NOT EXISTS schedules (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    route_id BIGINT(20) NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (route_id) REFERENCES routes(id)
);

-- SEATS TABLE
CREATE TABLE IF NOT EXISTS seats (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    seat_number VARCHAR(255),
    available TINYINT(1) NOT NULL DEFAULT 1,
    schedule_id BIGINT(20) NOT NULL,
    status VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    class VARCHAR(255),
    seat_class VARCHAR(255) NOT NULL,
    FOREIGN KEY (schedule_id) REFERENCES schedules(id)
);

-- BOOKINGS TABLE
CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT(20) NOT NULL,
    schedule_id BIGINT(20) NOT NULL,
    seat_id BIGINT(20) NOT NULL,
    booking_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    payment_status VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (schedule_id) REFERENCES schedules(id),
    FOREIGN KEY (seat_id) REFERENCES seats(id)
);
