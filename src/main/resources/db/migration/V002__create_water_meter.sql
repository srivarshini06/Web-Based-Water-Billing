CREATE TABLE IF NOT EXISTS water_meters (
    id BIGSERIAL PRIMARY KEY, resident_id BIGINT NOT NULL, meter_number VARCHAR(255) NOT NULL UNIQUE, installation_date DATE NOT NULL,
    initial_reading DOUBLE PRECISION NOT NULL, active BOOLEAN NOT NULL DEFAULT TRUE, created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_meter_resident FOREIGN KEY (resident_id) REFERENCES residents(id)
);
