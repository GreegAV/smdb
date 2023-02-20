insert into departments (id,dep_code, dep_name)
values
(0,'Unassigned', 'Unassigned');

insert into departments (dep_code, dep_name)
values
('IT', 'Information Technology'),
('Finance', 'Finance Department'),
('HR', 'Human Resources'),
('Support', 'Technical Support');

insert into employees (id,first_name, last_name, department_id, login, password, is_admin, email)
values
(0,'Admin', 'Admin', 0, 'admin', 'admin',  true, 'admin.admin@levi9.com');

insert into employees (first_name, last_name, department_id, login, password, is_admin, email)
values
('John', 'Dow', 1, 'johndow', 'johndow', false, 'john.dow@it.levi9.com'),
('Mary', 'Smith', 2, 'marysmith', 'marysmith', false, 'marysmith@finance.levi9.com'),
('Ann', 'Flower', 3, 'annflower', 'annflower',  false, 'ann.flower@hr.levi9.com'),
('Mike', 'Harder', 4, 'mikeharder', 'mikeharder', false, 'mike.harder@support.levi9.com'),
('Alex', 'Trainer', 3, 'alextrainer', 'alextrainer', false, 'alex.trainer@hr.levi9.com');

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
