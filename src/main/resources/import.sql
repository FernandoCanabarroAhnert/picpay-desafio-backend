INSERT INTO wallet_type (description) VALUES ('USER');
INSERT INTO wallet_type (description) VALUES ('MERCHANT');

INSERT INTO wallets (full_Name,cpf_Cnpj,email,password,balance,wallet_type_id) VALUES ('Maria','12345678910','maria@gmail.com','123456',100.0,1);
INSERT INTO wallets (full_Name,cpf_Cnpj,email,password,balance,wallet_type_id) VALUES ('Alex','01987654321','alex@gmail.com','123456',70.0,2);
INSERT INTO wallets (full_Name,cpf_Cnpj,email,password,balance,wallet_type_id) VALUES ('Bob','10918273645','bob@gmail.com','123456',120.0,1);