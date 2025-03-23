CREATE TABLE currency_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    currency_code VARCHAR(255) NOT NULL UNIQUE,
    currency_name VARCHAR(255) NOT NULL,
    symbol VARCHAR(255) NOT NULL,
    rate VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    rate_float DECIMAL(38, 18) 
);

INSERT INTO currency_mapping (currency_code, currency_name,symbol,rate,description,rate_float) VALUES ('USD', '美元' ,'&#36;' , '57,756.298'  ,'United States Dollar' ,57756.2984);
INSERT INTO currency_mapping (currency_code, currency_name,symbol,rate,description,rate_float) VALUES ('EUR', '歐元' ,'&euro;' , '52,243.287'  ,'Euro' ,52243.2865);
INSERT INTO currency_mapping (currency_code, currency_name,symbol,rate,description,rate_float) VALUES ('GBP', '英鎊' ,'&pound;' , '43,984.02'  ,'British Pound Sterling' ,43984.0203);