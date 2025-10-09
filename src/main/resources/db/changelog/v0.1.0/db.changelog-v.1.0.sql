--liquibase formatted sql
--changeset vshein:1.0

create table if not exists limits
    (
    id int4 NOT NULL,
    amount numeric NOT NULL,
    last_oper_id uuid NULL,
    oper_date date NULL,
    constraint limit_pkey primary key (id),
    constraint limit_id_unique unique (user_id, oper_date)
    );

comment on table limits is 'лимиты за день по клиентам';

create table if not exists operation
    (
    id uuid NOT NULL,
    amount numeric NOT NULL,
    type_operation varchar(25) NOT NULL,
    limit_id int4 NOT NULL,
    status varchar(25) NULL,
    constraint operation_pkey primary key (id),
    constraint operaton_limit_id_fk FOREIGN KEY (limit_id) REFERENCES limits (id) ON DELETE CASCADE
    );

comment on table operation is 'список операций по лимиту за день';

create sequence if not exists limits_id_seq;
