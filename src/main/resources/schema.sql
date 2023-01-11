DROP TABLE IF exists departments ;
DROP TABLE IF exists  employees;

create table departments (
id int primary key not null,
dep_code varchar,
dep_name varchar);

create table employees (
id int primary key not null,
first_name varchar,
last_name varchar,
department_id int,
email varchar);

ALTER TABLE employees ADD CONSTRAINT fk_employees_departments FOREIGN KEY (department_id) REFERENCES departments (id);

   insert into departments (id, dep_code, dep_name)
values
(1, 'IT', 'Information Technology'),
(2, 'Finance', 'Finance Department'),
(3, 'HR', 'Human Resources'),
(4, 'Support', 'Technical Support');

insert into employees (id, first_name, last_name, department_id, email)
values
(1, 'John', 'Dow', 1, 'john.dow@it.levi9.com'),
(2, 'Mary', 'Smith', 2, 'marysmith@finance.levi9.com'),
(3, 'Ann', 'Flower', 3, 'ann.flower@hr.levi9.com'),
(4, 'Mike', 'Harder', 4, 'mike.harder@support.levi9.com');












