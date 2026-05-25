CREATE TABLE payment_records (
    idempotency_key  VARCHAR(100)    PRIMARY KEY,
    booking_id       BIGINT          NOT NULL REFERENCES bookings(id),
    status           VARCHAR(20)     NOT NULL
                     CHECK (status IN ('SUCCESS', 'FAILED')),
    amount           NUMERIC(10, 2)  NOT NULL,
    processed_at     TIMESTAMP       NOT NULL DEFAULT now()
);
