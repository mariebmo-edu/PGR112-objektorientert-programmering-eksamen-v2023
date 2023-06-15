CREATE DATABASE IF NOT EXISTS universityDb;

USE universityDb;

CREATE TABLE IF NOT EXISTS  program(
    id int not null auto_increment primary key,
    program_name varchar(256)
);

CREATE TABLE IF NOT EXISTS role(
    id int not null auto_increment primary key,
    role_name varchar(256)
);

CREATE TABLE IF NOT EXISTS student(
    id int not null auto_increment primary key,
    first_name varchar(256),
    role_id int not null,
    program_id int not null,
    FOREIGN KEY (program_id) references program(id),
    FOREIGN KEY (role_id) references role(id)
);

CREATE TABLE IF NOT EXISTS staff(
    id int not null,
    first_name varchar(256),
    role_id int not null,
    FOREIGN KEY (role_id) references role(id)
);

CREATE TABLE IF NOT EXISTS guest(
    id int not null auto_increment primary key,
    first_name varchar(256),
    role_id int not null,
    student_id int not null,
    FOREIGN KEY (role_id) references role(id),
    FOREIGN KEY (student_id) references student(id)
);