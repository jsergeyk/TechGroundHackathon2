CREATE TABLE infrastructure
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    latitude    double,
    longitude   double,
    description TEXT

);

CREATE TABLE route_help_infrastructure
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    description  TEXT,
    latitude  double,
    longitude double
);


CREATE TABLE IF NOT EXISTS attribute
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS attribute_infrastructure
(
    attribute_id      BIGINT,
    infrastructure_id BIGINT,
    FOREIGN KEY (attribute_id) references attribute (id),
    FOREIGN KEY (infrastructure_id) references infrastructure (id),
    PRIMARY KEY (attribute_id, infrastructure_id)
);