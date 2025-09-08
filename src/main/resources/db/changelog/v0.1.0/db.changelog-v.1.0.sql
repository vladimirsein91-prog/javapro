--liquibase formatted sql
--changeset vshein:1.0

CREATE TABLE users (
                       id int PRIMARY KEY,
                       user_name VARCHAR(255) UNIQUE NOT NULL
);