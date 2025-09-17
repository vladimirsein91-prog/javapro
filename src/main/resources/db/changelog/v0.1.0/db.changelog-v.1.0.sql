--liquibase formatted sql
--changeset vshein:1.0

CREATE TABLE users (
                       id int PRIMARY KEY,
                       user_name VARCHAR(255) UNIQUE NOT NULL,
                       department_id int
);


CREATE TABLE department (
                       id int PRIMARY KEY,
                       name VARCHAR(255) UNIQUE NOT NULL
);