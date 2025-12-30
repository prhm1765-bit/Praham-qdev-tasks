USE employee;

--------------------------Create Table-------------------------

CREATE TABLE hobby(id varchar(10) PRIMARY KEY,
name varchar(100));

CREATE TABLE employee (id varchar(10) PRIMARY KEY,
first_name varchar(100), 
last_name varchar(100), 
age TINYINT UNSIGNED ,
mobile_number varchar(15), 
address varchar(100));

CREATE TABLE employee_salary(id varchar(10) PRIMARY KEY, 
salary bigint(255), 
salary_date DATE, 
emp_id varchar(10),
FOREIGN KEY(emp_id) REFERENCES employee(id));

CREATE TABLE employee_hobby(id varchar(10) PRIMARY KEY, 
hobby_id varchar(10), 
emp_id varchar(10),
FOREIGN KEY(emp_id) REFERENCES employee(id),
FOREIGN KEY(hobby_id) REFERENCES hobby(id));

--------------------------Insert Data---------------------------

INSERT INTO hobby (id, name) VALUES
('1', 'Cricket'),
('2', 'Football'),
('3', 'Reading'),
('4', 'Music'),
('5', 'Traveling'),
('6', 'Photography'),
('7', 'Gaming');

INSERT INTO employee (id, first_name, last_name, age, mobile_number, address) VALUES
('1', 'Praham',  'Patel', 20, '9876543210', 'Ahmedabad'),
('2', 'Jayesh',   'Soni',  21, '9876543211', 'Mumbai'),
('3', 'Suhani',   'Shah',  28, '9876543212', 'Pune'),
('4', 'Ravi',   'Kumar',  35, '9876543213', 'Bangalore'),
('5', 'Priya',  'Patel',  26, '9876543214', 'Ahmedabad'),
('6', 'Ankit',  'Mehta',  32, '9876543215', 'Surat'),
('7', 'Kiran',  'Rao',    29, '9876543216', 'Hyderabad'),
('8', 'Pooja',  'Nair',   27, '9876543217', 'Kochi'),
('9', 'Suman',  'Das',    34, '9876543218', 'Kolkata'),
('10','Rohit',  'Joshi',  31, '9876543219', 'Jaipur');

INSERT INTO employee_salary (id, salary, salary_date, emp_id) VALUES
('1',  40000, '2024-01-01', '1'),
('2',  55000, '2024-01-01', '2'),
('3',  48000, '2024-01-01', '3'),
('4',  65000, '2024-01-01', '4'),
('5',  42000, '2024-01-01', '5'),
('6',  60000, '2024-01-01', '6'),
('7',  52000, '2024-01-01', '7'),
('8',  47000, '2024-01-01', '8'),
('9',  70000, '2024-01-01', '9'),
('10', 58000, '2024-01-01', '10');

INSERT INTO employee_hobby (id, hobby_id, emp_id) VALUES
('1',  '1', '1'),
('2',  '3', '1'),
('3',  '2', '2'),
('4',  '4', '2'),
('5',  '5', '3'),
('6',  '1', '4'),
('7',  '6', '4'),
('8',  '7', '5'),
('9',  '3', '6'),
('10', '2', '7'),
('11', '4', '8'),
('12', '5', '9'),
('13', '6', '10'),
('14', '7', '10');

--------------------------------Update Data------------------------------

UPDATE hobby
SET name = 'Indoor Gaming'
WHERE id = '7';

UPDATE employee
SET address = 'Noida'
WHERE id = '2';

UPDATE employee_salary
SET salary = 45000
WHERE emp_id = '1';

UPDATE employee_hobby
SET hobby_id = '5'
WHERE emp_id = '2' AND hobby_id = '2';

----------------------------------Delete Data------------------------------

DELETE FROM employee_hobby
WHERE id IN ('14', '13','8', '12');

DELETE FROM employee_salary 
WHERE id IN ('10', '9');

DELETE FROM employee 
WHERE id IN ('10', '9');

DELETE FROM hobby 
WHERE id IN ('7')

---------------------------------Truncate Tables-------------------------

SET FOREIGN_KEY_CHECKS = 0;

Truncate TABLE employee_salary  

Truncate TABLE employee_hobby 

Truncate TABLE hobby 

Truncate TABLE employee

SET FOREIGN_KEY_CHECKS = 1;

----------------------------------Select Tables---------------------------

SELECT * FROM hobby;

SELECT * FROM employee;

SELECT * FROM employee_salary;

SELECT * FROM employee_hobby;

--Create a select single query to get all employee name, all hobby_name in single column

SELECT first_name FROM employee
UNION ALL
SELECT name FROM hobby

--Create a select query to get  employee name, his/her employee_salary

SELECT e.first_name, es.salary 
FROM employee e 
LEFT JOIN employee_salary es
ON e.id = es.emp_id ;

--Create a select query to get employee name, total salary of employee, hobby name(comma-separated - you need to use subquery for hobby name).

SELECT e.first_name, es.salary AS total_salary, (SELECT GROUP_CONCAT(h.name SEPARATOR ', ') FROM employee_hobby eh LEFT JOIN	hobby h ON	eh.emp_id = e.id) AS hobbies 
FROM employee e 
LEFT JOIN employee_salary es
ON e.id = es.emp_id ; 







