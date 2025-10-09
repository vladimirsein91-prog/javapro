CREATE TABLE if not exists product (
                       id int PRIMARY KEY,
                       account_number Varchar(50),
                       amount decimal,
                       type_product Varchar(10),
                       user_id int,
                       constraint fk_product_user foreign key (user_id) references users (id)
);

INSERT INTO product(id, account_number, amount, type_product, user_id)
VALUES (1, '12121212', 100, 'account', 1);

INSERT INTO product(id, account_number, amount, type_product, user_id)
VALUES (2, '999999999', 200, 'card', 1);

INSERT INTO product(id, account_number, amount, type_product, user_id)
VALUES (3, '12121212', 100, 'account', 2);

INSERT INTO product(id, account_number, amount, type_product, user_id)
VALUES (4, '999999999', 200, 'card', 2);
