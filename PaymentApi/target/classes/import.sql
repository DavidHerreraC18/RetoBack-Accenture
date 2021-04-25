--Users
INSERT INTO user_client(id, name, email, address, document, document_type, actual_client_order_id) values (1, 'David Herrera', 'david18hc@gmail.com', 'Carrera 11# 14-08','CC', '12345', null);
INSERT INTO user_client(id, name, email, address, document, document_type, actual_client_order_id) values (2, 'Pedrito Casas', 'pedrito18ch@gmail.com', 'Carrera 14# 18-70','CC', '54321', null);
INSERT INTO user_client(id, name, email, address, document, document_type, actual_client_order_id) values (3, 'Juanito Hernandez', 'juanito18hc@gmail.com', 'Carrera 17# 19-98','CC', '52431', null);

--Products
INSERT INTO product(id, name, price) VALUES (1, 'Vinilo Edicion Estandar', 30000);
INSERT INTO product(id, name, price) VALUES (2, 'Vinilo Edicion Deluxe', 70000);

--Orders
INSERT INTO client_order(id, delivery_cost, generation_date, state, total_cost, user_client_id) values (1, 5000, parsedatetime('04-24-2021 17:30:30', 'dd-MM-yyyy hh:mm:ss'), 'Completed', 0, 1);
UPDATE user_client SET actual_client_order_id = 1 WHERE id = 1;

--OrderProducts
INSERT INTO order_product(id, cost, quantity, client_order_id, product_id) values (1, 90000, 3, 1, 1);
---INSERT INTO order_product(id, cost, quantity, client_order_id, product_id) values (2, 70000, 1, 1, 2);

