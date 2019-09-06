use emusicstore;
INSERT INTO Authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
--INSERT INTO Authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO Users(username, password, enabled, customerId) VALUES ('admin', 'admin',true,1);
INSERT INTO Customer(customerEmail, customerName, customerPhone, enabled, password, username) VALUES ('admin', 'admin','admin',true,'admin','admin');
--
--INSERT INTO ShippingAddress(apartmentNumber,city,country,state,streetName,zipCode,customer_customerId)  
--SELECT 'Joe The Student', id_teacher
--  FROM Customer
-- WHERE customerName = 'admin'
--('admin','admin','admin','admin','admin','admin',1);
--INSERT INTO BillingAddress(apartmentNumber,city,country,state,streetName,zipCode,customer_customerId) VALUES ('admin','admin','admin','admin','admin','admin',1)
--INSERT INTO Users(username, password, enabled, customerId) VALUES ('user', 'user',true);

