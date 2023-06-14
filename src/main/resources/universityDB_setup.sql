CREATE DATABASE IF NOT EXISTS universityDB;

CREATE TABLE IF NOT EXISTS  program(
    id int not null auto_increment primary key,
    program_name varchar(256)
);

CREATE TABLE IF NOT EXISTS student(
    id int not null auto_increment primary key,
    first_name varchar(256),
    program_id int not null,
    FOREIGN KEY (program_id) references program(id)
);

CREATE TABLE IF NOT EXISTS staff(
    id int not null 
)