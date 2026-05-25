CREATE TABLE events (
    id               BIGSERIAL PRIMARY KEY,
    name             VARCHAR(200)        NOT NULL,
    venue            VARCHAR(200)        NOT NULL,
    event_date       TIMESTAMP           NOT NULL,
    total_seats      INT                 NOT NULL CHECK (total_seats > 0),
    available_seats  INT                 NOT NULL CHECK (available_seats >= 0),
    price            NUMERIC(10, 2)      NOT NULL CHECK (price >= 0),
    status           VARCHAR(20)         NOT NULL DEFAULT 'UPCOMING'
                     CHECK (status IN ('UPCOMING', 'ONGOING', 'CANCELLED')),
    created_by       BIGINT              REFERENCES users(id),   -- admin who created it
    created_at       TIMESTAMP           NOT NULL DEFAULT now(),
    updated_at       TIMESTAMP           NOT NULL DEFAULT now()
);