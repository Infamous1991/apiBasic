USE practice;

SET FOREIGN_KEY_CHECKS= 0;
TRUNCATE TABLE regions;
TRUNCATE TABLE countries;
TRUNCATE TABLE locations;
TRUNCATE TABLE jobs;
TRUNCATE TABLE departments;
TRUNCATE TABLE employees;
TRUNCATE TABLE dependents;
SET FOREIGN_KEY_CHECKS= 1;

-- @block
USE practice;

INSERT INTO regions (region_name) VALUES
('North America'),
('South America'),
('Europe'),
('Asia'),
('Africa');

-- @block
USE practice;

INSERT INTO countries (country_id, country_name, region_id) VALUES
('US', 'United States', 1),
('CA', 'Canada', 1),
('MX', 'Mexico', 1),
('GT', 'Guatemala', 1),
('CU', 'Cuba', 1),
('BR', 'Brazil', 2),
('AR', 'Argentina', 2),
('CO', 'Colombia', 2),
('PE', 'Peru', 2),
('CL', 'Chile', 2),
('DE', 'Germany', 3),
('FR', 'France', 3),
('IT', 'Italy', 3),
('ES', 'Spain', 3),
('GB', 'United Kingdom', 3),
('CN', 'China', 4),
('IN', 'India', 4),
('JP', 'Japan', 4),
('KR', 'South Korea', 4),
('VN', 'Vietnam', 4),
('ZA', 'South Africa', 5),
('EG', 'Egypt', 5),
('NG', 'Nigeria', 5),
('KE', 'Kenya', 5),
('MA', 'Morocco', 5);

-- @block
USE practice;

INSERT INTO locations (street_address, postal_code, city, state_province, country_id) VALUES
('123 Main St', '10001', 'New York', 'New York', 'US'),
('456 Elm St', 'H3Z 2Y7', 'Montreal', 'Quebec', 'CA'),
('789 Oak Ave', '11000', 'Mexico City', 'Mexico City', 'MX'),
('1 Maya Blvd', '01001', 'Guatemala City', NULL, 'GT'),
('2 José Martí St', '10400', 'Havana', NULL, 'CU'),
('Av Paulista 1000', '01310-100', 'Sao Paulo', 'Sao Paulo', 'BR'),
('Calle Florida 123', 'C1005AAA', 'Buenos Aires', 'Buenos Aires', 'AR'),
('Carrera 7 #45-12', '110231', 'Bogota', 'Cundinamarca', 'CO'),
('Av Javier Prado 456', '15034', 'Lima', 'Lima', 'PE'),
('Calle Arturo Prat', '8320000', 'Santiago', 'Santiago Metropolitan', 'CL'),
('Unter den Linden 7', '10117', 'Berlin', 'Berlin', 'DE'),
('10 Rue de Rivoli', '75001', 'Paris', 'Île-de-France', 'FR'),
('Via Roma 15', '00100', 'Rome', 'Lazio', 'IT'),
('Calle Mayor 22', '28013', 'Madrid', 'Madrid', 'ES'),
('221B Baker Street', 'NW1 6XE', 'London', 'England', 'GB'),
('1 Zhongguancun St', '100080', 'Beijing', 'Beijing', 'CN'),
('123 MG Road', '110001', 'New Delhi', 'Delhi', 'IN'),
('Shibuya 2-21-1', '150-0002', 'Tokyo', 'Tokyo', 'JP'),
('25 Gangnam-daero', '06236', 'Seoul', 'Seoul', 'KR'),
('1 Phạm Ngọc Thạch', '70000', 'Ho Chi Minh City', NULL, 'VN'),
('1 Long St', '8001', 'Cape Town', 'Western Cape', 'ZA'),
('15 Tahrir Sq', '11511', 'Cairo', NULL, 'EG'),
('123 Lagos Main Rd', '100001', 'Lagos', 'Lagos State', 'NG'),
('456 Moi Ave', '00100', 'Nairobi', NULL, 'KE'),
('7 Rue Hassan II', '20100', 'Casablanca', 'Casablanca-Settat', 'MA');

-- @block
USE practice;

INSERT INTO jobs (job_title, min_salary, max_salary) VALUES
('Software Engineer', 60000.00, 120000.00),
('Data Scientist', 65000.00, 130000.00),
('Project Manager', 50000.00, 100000.00),
('HR Specialist', 40000.00, 80000.00),
('Graphic Designer', 35000.00, 70000.00),
('Sales Executive', 30000.00, 60000.00),
('Accountant', 45000.00, 85000.00),
('Mechanical Engineer', 55000.00, 110000.00),
('Marketing Manager', 50000.00, 95000.00),
('Business Analyst', 60000.00, 115000.00);

-- @block
USE practice;

INSERT INTO departments (department_name, location_id) VALUES
('Human Resources', 1),
('Engineering', 2),
('Marketing', 3),
('Sales', 4),
('Finance', 5),
('Customer Support', 6),
('Research and Development', 7),
('IT Services', 8),
('Operations', 9),
('Logistics', 10);

-- @block
USE practice;

INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id) VALUES
('Alice', 'Smith', 'alice.smith@example.com', '555-1234', '2020-01-15', 2, 85000.00, NULL, 2),
('Bob', 'Johnson', 'bob.johnson@example.com', '555-2345', '2021-03-10', 1, 75000.00, 1, 2),
('Carol', 'Williams', 'carol.williams@example.com', '555-3456', '2019-07-22', 9, 65000.00, NULL, 3),
('David', 'Brown', 'david.brown@example.com', '555-4567', '2022-05-18', 10, 72000.00, 3, 3),
('Eve', 'Jones', 'eve.jones@example.com', '555-5678', '2018-09-01', 6, 55000.00, NULL, 4),
('Frank', 'Miller', 'frank.miller@example.com', '555-6789', '2020-11-30', 6, 60000.00, 5, 4),
('Grace', 'Davis', 'grace.davis@example.com', '555-7890', '2017-02-14', 7, 90000.00, NULL, 5),
('Hank', 'Wilson', 'hank.wilson@example.com', '555-8901', '2023-06-05', 7, 92000.00, 7, 5),
('Ivy', 'Moore', 'ivy.moore@example.com', '555-9012', '2021-04-12', 8, 80000.00, NULL, 8),
('Jack', 'Taylor', 'jack.taylor@example.com', '555-0123', '2022-08-21', 1, 85000.00, 9, 8);


-- @block
USE practice;

INSERT INTO dependents (first_name, last_name, relationship, employee_id) VALUES
('Lily', 'Smith', 'Daughter', 1),
('John', 'Smith', 'Son', 1),
('Emily', 'Johnson', 'Spouse', 2),
('Sophia', 'Williams', 'Daughter', 3),
('David Jr.', 'Williams', 'Son', 3),
('Mark', 'Jones', 'Spouse', 5),
('Michael', 'Davis', 'Spouse', 7),
('Sarah', 'Davis', 'Daughter', 7),
('Oliver', 'Moore', 'Son', 9),
('Ethan', 'Moore', 'Son', 9);