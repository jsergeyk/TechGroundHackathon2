CREATE TABLE infrastructura
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    availabily VARCHAR(45),
    description VARCHAR(5000),
    coordinates VARCHAR(100)
);