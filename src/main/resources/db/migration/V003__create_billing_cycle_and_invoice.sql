CREATE TABLE IF NOT EXISTS billing_cycles (
    id BIGSERIAL PRIMARY KEY, community_id BIGINT NOT NULL, start_date DATE NOT NULL, end_date DATE NOT NULL, status VARCHAR(30) NOT NULL,
    finalized_at TIMESTAMP, archived_at TIMESTAMP, total_amount DOUBLE PRECISION NOT NULL DEFAULT 0, total_invoices_generated BIGINT NOT NULL DEFAULT 0, created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_cycle_community FOREIGN KEY (community_id) REFERENCES communities(id),
    CONSTRAINT uk_cycle_dates UNIQUE (community_id, start_date, end_date)
);
CREATE TABLE IF NOT EXISTS invoices (
    id BIGSERIAL PRIMARY KEY, billing_cycle_id BIGINT NOT NULL, resident_id BIGINT NOT NULL, water_consumption NUMERIC(14,2) NOT NULL, water_charge NUMERIC(14,2) NOT NULL,
    procurement_charge NUMERIC(14,2) NOT NULL DEFAULT 0, shared_area_allocation NUMERIC(14,2) NOT NULL DEFAULT 0, adjustment NUMERIC(14,2) NOT NULL DEFAULT 0,
    total_amount NUMERIC(14,2) NOT NULL, status VARCHAR(20) NOT NULL DEFAULT 'PENDING', created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_invoice_cycle FOREIGN KEY (billing_cycle_id) REFERENCES billing_cycles(id), CONSTRAINT fk_invoice_resident FOREIGN KEY (resident_id) REFERENCES residents(id),
    CONSTRAINT uk_invoice_cycle_resident UNIQUE (billing_cycle_id, resident_id)
);
