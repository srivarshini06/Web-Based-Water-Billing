-- Baseline for the schema that already existed before Flyway.
-- IF NOT EXISTS keeps this migration safe for an existing database while
-- also allowing a fresh database to start from zero.
CREATE TABLE IF NOT EXISTS users (
    user_id BIGSERIAL PRIMARY KEY, full_name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, role VARCHAR(50) NOT NULL, status VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS communities (
    id BIGSERIAL PRIMARY KEY, community_name VARCHAR(255) NOT NULL, owner_name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255) NOT NULL, address VARCHAR(255) NOT NULL, status VARCHAR(50) NOT NULL, created_at TIMESTAMP, approved_at TIMESTAMP,
    admin_user_id BIGINT UNIQUE, CONSTRAINT fk_community_admin FOREIGN KEY (admin_user_id) REFERENCES users(user_id)
);
CREATE TABLE IF NOT EXISTS residents (
    id BIGSERIAL PRIMARY KEY, full_name VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE, phone_number VARCHAR(255) NOT NULL,
    building VARCHAR(255) NOT NULL, block VARCHAR(255) NOT NULL, flat_number VARCHAR(255) NOT NULL, invited BOOLEAN DEFAULT FALSE, registered BOOLEAN DEFAULT FALSE,
    community_id BIGINT, CONSTRAINT fk_resident_community FOREIGN KEY (community_id) REFERENCES communities(id)
);
CREATE TABLE IF NOT EXISTS consumers (
    id BIGSERIAL PRIMARY KEY, consumer_name VARCHAR(255) NOT NULL, connection_number VARCHAR(255) NOT NULL UNIQUE, address VARCHAR(255) NOT NULL, phone_number VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS water_tariffs (
    id BIGSERIAL PRIMARY KEY, community_id BIGINT NOT NULL, price_per_litre DOUBLE PRECISION NOT NULL, effective_from DATE NOT NULL, active BOOLEAN NOT NULL,
    CONSTRAINT fk_tariff_community FOREIGN KEY (community_id) REFERENCES communities(id)
);
CREATE TABLE IF NOT EXISTS tariff_tiers (
    id BIGSERIAL PRIMARY KEY, tariff_id BIGINT NOT NULL, min_litres DOUBLE PRECISION NOT NULL, max_litres DOUBLE PRECISION, price_per_litre DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_tier_tariff FOREIGN KEY (tariff_id) REFERENCES water_tariffs(id)
);
CREATE TABLE IF NOT EXISTS water_readings (
    id BIGSERIAL PRIMARY KEY, resident_id BIGINT NOT NULL, reading_date DATE NOT NULL, previous_reading DOUBLE PRECISION NOT NULL,
    current_reading DOUBLE PRECISION NOT NULL, consumption DOUBLE PRECISION NOT NULL, tariff_per_litre DOUBLE PRECISION NOT NULL, amount DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_reading_resident FOREIGN KEY (resident_id) REFERENCES residents(id)
);
CREATE TABLE IF NOT EXISTS bills (
    id BIGSERIAL PRIMARY KEY, reading_id BIGINT NOT NULL UNIQUE, resident_id BIGINT NOT NULL, amount DOUBLE PRECISION NOT NULL, consumption DOUBLE PRECISION NOT NULL,
    bill_month DATE NOT NULL, paid BOOLEAN NOT NULL DEFAULT FALSE, paid_date DATE,
    CONSTRAINT fk_bill_reading FOREIGN KEY (reading_id) REFERENCES water_readings(id), CONSTRAINT fk_bill_resident FOREIGN KEY (resident_id) REFERENCES residents(id)
);
CREATE TABLE IF NOT EXISTS bulk_water_purchases (
    id BIGSERIAL PRIMARY KEY, community_id BIGINT NOT NULL, purchase_date DATE NOT NULL, quantity_litres DOUBLE PRECISION NOT NULL, price_per_litre DOUBLE PRECISION NOT NULL,
    total_cost DOUBLE PRECISION NOT NULL, supplier_name VARCHAR(255), invoice_number VARCHAR(255), active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_purchase_community FOREIGN KEY (community_id) REFERENCES communities(id)
);
CREATE TABLE IF NOT EXISTS complaints (
    id BIGSERIAL PRIMARY KEY, resident_id BIGINT NOT NULL, subject VARCHAR(255) NOT NULL, description VARCHAR(2000) NOT NULL, status VARCHAR(50), created_at TIMESTAMP, resolved_at TIMESTAMP,
    CONSTRAINT fk_complaint_resident FOREIGN KEY (resident_id) REFERENCES residents(id)
);
