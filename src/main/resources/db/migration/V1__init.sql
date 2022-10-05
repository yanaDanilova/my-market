
CREATE TABLE categories (id bigserial PRIMARY KEY, title VARCHAR(255));
INSERT INTO categories (title) VALUES ('Clothes');




CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int, category_id bigint references categories(id));
INSERT INTO products (title, price, category_id) VALUES
('T-Shirt', 25, 1),
('Jeans', 70,1),
('Sweater',150,1),
('Skirt',50,1),
('Coat',150,1),
('Hat', 70,1),
('Scarf',68,1),
('Suit', 350,1),
('Dress', 150,1),
('Jeans', 78,1),
('Sweater',100,1)
;

