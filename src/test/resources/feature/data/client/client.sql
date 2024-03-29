CREATE TABLE client (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    address_id BIGINT UNIQUE,
    PRIMARY KEY (ID)
);

ALTER TABLE IF EXISTS client ADD CONSTRAINT fk_client_address_id FOREIGN KEY (address_id) REFERENCES address;