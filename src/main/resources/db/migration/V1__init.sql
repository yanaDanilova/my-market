
CREATE TABLE categories (id bigserial PRIMARY KEY, title VARCHAR(255),
created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp);
INSERT INTO categories (title) VALUES ('Clothes');

CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price numeric(8,2),
 category_id bigint references categories(id),
 created_at timestamp default current_timestamp,
 updated_at timestamp default current_timestamp);

INSERT INTO products (title, price, category_id) VALUES
('T-Shirt', 25.50, 1),
('Jeans', 70.25,1),
('Sweater',150.99,1),
('Skirt',50.45,1),
('Coat',150.88,1),
('Hat', 70.55,1),
('Scarf',68.99,1),
('Suit', 350.55,1),
('Dress', 150.25,1),
('Jeans', 78.66,1),
('Sweater',100.00,1)
;

create table order_items (
    id                              bigserial primary key,
    product_id                      bigint references products (id),
    quantity                        int,
    price_per_product               numeric (8, 2),
    price                           numeric (8, 2),
    created_at                      timestamp default current_timestamp,
    updated_at                      timestamp default current_timestamp
);

create table users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(80) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table roles (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
          user_id                 bigint not null references users (id),
          role_id                 bigint not null references roles (id),
          primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'user@gmail.com'),
('admin', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

