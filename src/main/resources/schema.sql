DROP TABLE IF exists software;
DROP TABLE IF exists employees;
DROP TABLE IF exists departments;

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

create table software (
id int primary key not null,
soft_name varchar,
serial varchar,
assigned_to int);

ALTER TABLE software ADD CONSTRAINT fk_software_employees FOREIGN KEY (assigned_to) REFERENCES employees (id);


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
(4, 'Mike', 'Harder', 4, 'mike.harder@support.levi9.com'),
(5, 'Alex', 'Trainer', 3, 'alex.trainer@hr.levi9.com');

insert into software  (id, soft_name , serial, assigned_to)
values
(1, 'Microsoft Windows 10', 'MS-WIN-10', 1),
(2, 'Adobe Photoshop', 'ADOBE-PS-CC',3),
(3, 'WinRAR', 'FREE',1),
(4, 'WinRAR', 'FREE',2),
(5, 'WinRAR', 'FREE',3),
(6, 'WinRAR', 'FREE',4),
(7, 'Microsoft Windows 11', 'MS-WIN-11', 3),
(8, 'Microsoft Office', 'MSO-365', 2),
(9, 'Teamviewer', 'TEAM-23', 4);












