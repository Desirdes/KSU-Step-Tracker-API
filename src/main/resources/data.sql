-- Insert User data
--INSERT INTO user (username, password, role, groupID) VALUES
--('mando', '$2a$10$5a9H7r5.hiKWdU6yvU6yKO/ZNyvj2Ywq3ZfMd2lEiX7DITeLChfCK','ROLE_USER',1),
--('alex', '$2a$10$52GwD1BYeLxR/a9X0oGtjOq3ScUN2Q0o0aFbK1Tqtpwp.TJlC/jwe','ROLE_USER',1),
--('david', '$2a$10$v8b4GhO4A4HlyYBLwz0JZuPOulcK9NCm2oz.K.IkDh.xzzgzbk4LK','ROLE_USER',1);

INSERT INTO person (full_name, email, demographic, gender, age, username) VALUES
('Armando', 'armando@ksu.com', 'American', 'M', 32, 'anegrona'),
('Alex', 'alex@ksu.com', 'American', 'F', 33, 'afulle62'),
('David', 'david@ksue.com', 'American', 'M', 29, 'dsanfor5');

-- Insert Biometric data
INSERT INTO biometric (height, weight, waist_circumference, neck_circumference, body_fat_percentage, person_id) VALUES
(170, 75, 85, 35, 18.5, 1),
(170, 75, 85, 35, 18.5, 2),
(170, 75, 85, 35, 18.5, 3);

-- Insert Target data
INSERT INTO target (daily_steps, weight_loss, weight_loss_percentage, person_id) VALUES
(10000, 10, 5, 1),
(10000, 11, 3, 2),
(10000, 15, 4, 3);

-- Insert Activity data
INSERT INTO activity (steps, date, person_id) VALUES
(8500, '2022-03-20', 1),
(12000, '2022-03-20', 2),
(7000, '2022-03-20', 3),
(9500, '2022-03-19', 1),
(13000, '2022-03-19', 2),
(7500, '2022-03-19', 3),
(8000, '2022-03-18', 1),
(11000, '2022-03-18', 2),
(6500, '2022-03-18', 3),
(7500, '2022-03-17', 1),
(10000, '2022-03-17', 2),
(6000, '2022-03-17', 3),
(7000, '2022-03-16', 1),
(9000, '2022-03-16', 2),
(5500, '2022-03-16', 3);
