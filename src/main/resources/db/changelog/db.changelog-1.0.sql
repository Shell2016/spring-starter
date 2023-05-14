--liquibase formatted sql

--changeset michaelshell:1
CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY ,
    username VARCHAR(64) NOT NULL UNIQUE ,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    role VARCHAR(32),
    company_id INT REFERENCES company (id)
    );

--changeset michaelshell:4
CREATE TABLE IF NOT EXISTS payment
(
    id BIGSERIAL PRIMARY KEY ,
    amount INT NOT NULL ,
    receiver_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE
    );

--changeset michaelshell:5
CREATE TABLE IF NOT EXISTS chat
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
    );

--changeset michaelshell:6
CREATE TABLE IF NOT EXISTS users_chat
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE ,
    chat_id BIGINT NOT NULL REFERENCES chat (id) ON DELETE CASCADE ,
    UNIQUE (user_id, chat_id)
    );