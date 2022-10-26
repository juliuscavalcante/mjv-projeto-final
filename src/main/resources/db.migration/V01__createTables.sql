/*CREATE TABLE IF NOT EXISTS persons
(
    dtype      VARCHAR(31)  NOT NULL,
    id         BIGINT       NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name       VARCHAR(255) NOT NULL,
    cpf        VARCHAR(11)  NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    birth_date DATE,
    CONSTRAINT persons_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS profiles
(
    person_id BIGINT NOT NULL,
    profiles  INTEGER,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES persons (id)
);

CREATE TABLE IF NOT EXISTS requests
(
    id           BIGINT       NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    opening_date DATE,
    closing_date DATE,
    priority     INTEGER      NOT NULL,
    status       INTEGER      NOT NULL,
    title        VARCHAR(255) NOT NULL,
    notes        VARCHAR(255) NOT NULL,
    engineer_id  BIGINT,
    mechanic_id  BIGINT,
    CONSTRAINT requests_pkey PRIMARY KEY (id),
    CONSTRAINT fk_mechanic FOREIGN KEY (mechanic_id) REFERENCES persons (id),
    CONSTRAINT fk_engineer FOREIGN KEY (engineer_id) REFERENCES persons (id)
);


*/