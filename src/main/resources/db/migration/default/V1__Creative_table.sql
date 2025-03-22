CREATE TABLE currency_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    currency_code VARCHAR(10) UNIQUE NOT NULL,
    currency_name VARCHAR(50) NOT NULL
);
INSERT INTO currency_mapping (currency_code, currency_name) VALUES ('USD', '美元');
INSERT INTO currency_mapping (currency_code, currency_name) VALUES ('EUR', '歐元');
INSERT INTO currency_mapping (currency_code, currency_name) VALUES ('GBP', '英鎊');