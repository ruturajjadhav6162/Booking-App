CREATE TABLE bookings (
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT              NOT NULL REFERENCES users(id),
    event_id      BIGINT              NOT NULL REFERENCES events(id),
    status        VARCHAR(20)         NOT NULL DEFAULT 'PENDING'
                  CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED')),
    total_price   NUMERIC(10, 2)      NOT NULL,
    created_at    TIMESTAMP           NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP           NOT NULL DEFAULT now()
);
