CREATE TABLE seats (
    id            BIGSERIAL PRIMARY KEY,
    event_id      BIGINT              NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    seat_number   VARCHAR(20)         NOT NULL,
    status        VARCHAR(20)         NOT NULL DEFAULT 'AVAILABLE'
                  CHECK (status IN ('AVAILABLE', 'HELD', 'BOOKED')),
    version       BIGINT              NOT NULL DEFAULT 0,
    created_at    TIMESTAMP           NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP           NOT NULL DEFAULT now(),
    UNIQUE (event_id, seat_number)
);
