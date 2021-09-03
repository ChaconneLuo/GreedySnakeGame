use test;
create table user(
    id INTEGER auto_increment primary key,
    name VARCHAR(20),
    password VARCHAR(256)
);
create table data(
    id INTEGER auto_increment primary key,
    maxScore bigint
);