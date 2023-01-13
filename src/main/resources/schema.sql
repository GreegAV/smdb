DROP TABLE IF exists software;
DROP TABLE IF exists employees;
DROP TABLE IF exists departments;

create table departments (
id serial primary key not null,
dep_code varchar,
dep_name varchar);

create table employees (
id serial primary key not null,
first_name varchar,
last_name varchar,
department_id int,
email varchar);

ALTER TABLE employees ADD CONSTRAINT fk_employees_departments FOREIGN KEY (department_id) REFERENCES departments (id);

create table software (
id serial primary key not null,
soft_name varchar,
serial varchar,
assigned_to int);

ALTER TABLE software ADD CONSTRAINT fk_software_employees FOREIGN KEY (assigned_to) REFERENCES employees (id);

insert into departments (dep_code, dep_name)
values
('IT', 'Information Technology'),
('Finance', 'Finance Department'),
('HR', 'Human Resources'),
('Support', 'Technical Support');

insert into employees (first_name, last_name, department_id, email)
values
('John', 'Dow', 1, 'john.dow@it.levi9.com'),
('Mary', 'Smith', 2, 'marysmith@finance.levi9.com'),
('Ann', 'Flower', 3, 'ann.flower@hr.levi9.com'),
('Mike', 'Harder', 4, 'mike.harder@support.levi9.com'),
('Alex', 'Trainer', 3, 'alex.trainer@hr.levi9.com');

insert into software  (soft_name , serial, assigned_to)
values
('Microsoft Windows 10', 'MS-WIN-10', 1),
('Adobe Photoshop', 'ADOBE-PS-CC',3),
('WinRAR', 'FREE',1),
('WinRAR', 'FREE',2),
('WinRAR', 'FREE',3),
('WinRAR', 'FREE',4),
('Microsoft Windows 11', 'MS-WIN-11', 3),
('Microsoft Office', 'MSO-365', 2),
('Teamviewer', 'TEAM-23', 4),
('AnyDesk', 'FREE', null);












