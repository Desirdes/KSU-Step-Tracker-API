-- Insert User data
INSERT INTO users (id, username, password, roles, groupid, personid, reset_password) VALUES
(1, 'anegrona', '$2a$10$hawhRLPtIj/mmZdD7/2Og.CzcvrkKY3VNPRHa1ZVlBOfK3gLyLCaK','ROLE_USER',1, 1, false),
(2, 'afulle62', '$2a$10$G/rAK1NrOyx4GZ6k4i5XO.ONbePhZDfBTP1XPh9O6D6IC5INMhf6C','ROLE_USER,ROLE_ADMIN',1, 2, false),
(3, 'dsanfor5', '$2a$10$pIKoIvqMXm3pVpRXudwCheElow12BtdAQW2BTc3dn3r9hrx.7y6NK','ROLE_USER,ROLE_ADMIN',1, 3, false);

INSERT INTO person (full_name, email, demographic, gender, age, username, signup_date) VALUES
('Armando', 'armando@ksu.com', 'American', 'Male', 32, 'anegrona', '2023-1-1'),
('Alex', 'alex@ksu.com', 'American', 'Female', 33, 'afulle62', '2023-1-1'),
('David Sanford', 'pgmdes@gmail.com', 'American', 'Male', 29, 'dsanfor5', '2023-1-1');

-- Insert Biometric data
INSERT INTO biometric (height, weight, waist_circumference, neck_circumference, body_fat_percentage, person_id, date_updated) VALUES
(70, 75, 32, 20, 18.5, 1, '2023-1-1'),
(70, 75, 32, 20, 18.5, 2, '2023-1-1'),
(70, 210, 32, 20, 18.5, 3, '2023-1-1'),
(70, 205, 32, 20, 18.5, 3, '2023-4-1');

-- Insert Target data
INSERT INTO target (daily_steps, weight_loss, weight_loss_percentage, person_id, date_updated) VALUES
(10000, 10, 5, 1, '2023-1-1'),
(10000, 10, 3, 2, '2023-1-1'),
(15000, 15, 8, 3, '2023-1-1'),
(13000, 12, 6, 3, '2023-4-1');

-- Insert Activity data
INSERT INTO activity (steps, date, person_id) VALUES
(8500, '2023-03-20', 1),
(12000, '2023-03-20', 2),
(7000, '2023-03-20', 3),
(9500, '2023-03-19', 1),
(13000, '2023-03-19', 2),
(7500, '2023-03-19', 3),
(8000, '2023-03-18', 1),
(11000, '2023-03-18', 2),
(6500, '2023-03-18', 3),
(7500, '2023-03-17', 1),
(10000, '2023-03-17', 2),
(6000, '2023-03-17', 3),
(7000, '2023-03-16', 1),
(9000, '2023-03-16', 2),
(5500, '2023-03-16', 3),
(12200, '2023-04-1', 3),
(9000, '2023-04-3', 3),
(16000, '2023-04-7', 3),
(5500, '2023-04-12', 3);
