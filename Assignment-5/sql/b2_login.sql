USE csa09_test;
DROP TABLE IF EXISTS app_user;
CREATE TABLE app_user(username VARCHAR(40) PRIMARY KEY,password VARCHAR(100) NOT NULL,role VARCHAR(20) NOT NULL);
-- Demonstration-only plaintext credentials for reproducing the vulnerable example.
-- Production systems must store salted password hashes, never plaintext passwords.
INSERT INTO app_user VALUES('vignesh','java123','STUDENT'),('admin','admin123','ADMIN');
