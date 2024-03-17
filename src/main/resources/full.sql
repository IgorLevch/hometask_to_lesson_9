
create table if not exists products (id bigserial primary key, title varchar(255), cost int);


insert into products (title, cost)
values
('milk',80),
('bread', 100),
('coffee', 55),
('water', 99);