CREATE TABLE IF NOT EXISTS type
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS infrastructure
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    availability BOOLEAN,
    description  TEXT,
    coordinates  VARCHAR(255),
    type_id      BIGINT,
    foreign key (type_id) references type (id)
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