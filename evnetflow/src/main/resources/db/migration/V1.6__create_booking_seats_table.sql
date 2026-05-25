CREATE TABLE booking_seats (
    booking_id    BIGINT    NOT NULL REFERENCES bookings(id) ON DELETE CASCADE,
    seat_id       BIGINT    NOT NULL REFERENCES seats(id),
    PRIMARY KEY (booking_id, seat_id)
);
