CREATE TABLE IF NOT EXISTS water_usage_alerts (
    id BIGSERIAL PRIMARY KEY, resident_id BIGINT NOT NULL, alert_type VARCHAR(30) NOT NULL, message VARCHAR(500) NOT NULL, detected_value DOUBLE PRECISION NOT NULL,
    threshold DOUBLE PRECISION NOT NULL, acknowledged BOOLEAN NOT NULL DEFAULT FALSE, created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_alert_resident FOREIGN KEY (resident_id) REFERENCES residents(id)
);
