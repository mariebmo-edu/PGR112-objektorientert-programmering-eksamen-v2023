CREATE DATABASE IF NOT EXISTS eventDb;

USE eventDb;

CREATE TABLE IF NOT EXISTS event (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS event_registration(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    attendee_id INT NOT NULL,
    attendee_role_id INT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event(id)
)

