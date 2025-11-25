CREATE TABLE passport
(
    id              BIGSERIAL PRIMARY KEY,
    passport_number VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE resident
(
    id          BIGSERIAL PRIMARY KEY,
    passport_id BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_resident_passport FOREIGN KEY (passport_id) REFERENCES passport (id) ON DELETE CASCADE
);

CREATE TABLE car
(
    id           BIGSERIAL PRIMARY KEY,
    plate_number VARCHAR(20)  NOT NULL UNIQUE,
    model        VARCHAR(255) NOT NULL,
    owner_id     BIGINT,
    CONSTRAINT fk_car_owner FOREIGN KEY (owner_id) REFERENCES resident (id) ON DELETE SET NULL
);

CREATE TABLE house
(
    id      BIGSERIAL PRIMARY KEY,
    address VARCHAR(500) NOT NULL
);

CREATE TABLE resident_house
(
    resident_id BIGINT NOT NULL,
    house_id    BIGINT NOT NULL,
    CONSTRAINT pk_resident_house PRIMARY KEY (resident_id, house_id),
    CONSTRAINT fk_rh_resident FOREIGN KEY (resident_id) REFERENCES resident (id) ON DELETE CASCADE,
    CONSTRAINT fk_rh_house FOREIGN KEY (house_id) REFERENCES house (id) ON DELETE CASCADE
);

CREATE INDEX idx_car_owner ON car (owner_id);
