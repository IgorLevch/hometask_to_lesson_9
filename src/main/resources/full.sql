
DROP TABLE IF EXISTS products;

CREATE TABLE products(
    id bigserial NOT NULL,
    title varchar(100) NOT NULL,
    cost bigserial NOT NULL,
    mark bigserial NOT NULL,
    secretKey varchar (100),
    PRIMARY KEY(id)
    );

INSERT INTO products (title, cost, mark, secretkey) VALUES
('milk',80,5,'dddd'),
('bread', 100, 4, 'dffg7765ds'),
('coffee', 55, 5, 'dffg7765ds'),
('water', 99, 2, 'dffg7765ds');