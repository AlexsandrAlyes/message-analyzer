drop schema if exists db_log cascade;

create schema if not exists db_log;

comment on schema db_log is 'Логи очереди';

create table if not exists db_log.debug
(
    id              bigserial       not null primary key,
    system_type_id   char(255),
    method_params     varchar(1000)
);

create table if not exists db_log.exception
(
    id              bigserial       not null primary key,
    system_type_id   char(255),
    method_params     varchar(1000)
);