USE csa09_test;
CREATE TABLE IF NOT EXISTS bus_pass(pass_id VARCHAR(16) PRIMARY KEY,register_no VARCHAR(16) NOT NULL,name VARCHAR(80) NOT NULL,route_no VARCHAR(8) NOT NULL,boarding_stop VARCHAR(60) NOT NULL,valid_until DATE NOT NULL,amount_paise BIGINT NOT NULL,CHECK(amount_paise>0));
