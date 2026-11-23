CREATE DATABASE IF NOT EXISTS csa09_test;USE csa09_test;
CREATE TABLE IF NOT EXISTS event(event_id INT AUTO_INCREMENT PRIMARY KEY,event_name VARCHAR(120) NOT NULL UNIQUE,capacity INT NOT NULL CHECK(capacity>0));
CREATE TABLE IF NOT EXISTS registration(reg_id BIGINT AUTO_INCREMENT PRIMARY KEY,event_id INT NOT NULL,reg_no CHAR(9) NOT NULL,student_name VARCHAR(60) NOT NULL,email VARCHAR(160) NOT NULL,registered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,CONSTRAINT fk_event FOREIGN KEY(event_id) REFERENCES event(event_id),CONSTRAINT uq_one_registration UNIQUE(event_id,reg_no),INDEX idx_event_reg(event_id,reg_id));
INSERT IGNORE INTO event(event_name,capacity) VALUES('Tech Symposium 2026',500),('AI Workshop',120),('Coding Contest',200);
