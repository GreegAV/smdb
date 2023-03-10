DROP TABLE IF exists software cascade;
DROP TABLE IF exists employees cascade;
DROP TABLE IF exists departments cascade;

create table departments (
id int8 generated by default as identity primary key,
dep_code varchar,
dep_name varchar);

create table employees (
id int8 generated by default as identity primary key,
first_name varchar,
last_name varchar,
department_id int8,
login varchar,
password varchar,
is_admin boolean not null default false,
email varchar);

ALTER TABLE employees ADD CONSTRAINT fk_employees_departments FOREIGN KEY (department_id) REFERENCES departments (id);

create table software (
id int8 generated by default as identity primary key,
soft_name varchar,
serial varchar,
assigned_to int8);

ALTER TABLE software ADD CONSTRAINT fk_software_employees FOREIGN KEY (assigned_to) REFERENCES employees (id);

