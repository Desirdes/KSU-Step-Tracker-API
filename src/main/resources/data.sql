-- Insert User data
--INSERT INTO user (username, password, role, groupID) VALUES
--('mando', '$2a$10$5a9H7r5.hiKWdU6yvU6yKO/ZNyvj2Ywq3ZfMd2lEiX7DITeLChfCK','ROLE_USER',1),
--('alex', '$2a$10$52GwD1BYeLxR/a9X0oGtjOq3ScUN2Q0o0aFbK1Tqtpwp.TJlC/jwe','ROLE_USER',1),
--('david', '$2a$10$v8b4GhO4A4HlyYBLwz0JZuPOulcK9NCm2oz.K.IkDh.xzzgzbk4LK','ROLE_USER',1);

--INSERT INTO PERSON (id, first_name, last_name, gender, age) VALUES (1 , 'Armando', 'Negron', 'male', 33);
--INSERT INTO PERSON (id, first_name, last_name, gender, age) VALUES (2 , 'Alex', 'Fuller', 'female', 33);
--INSERT INTO PERSON (id, first_name, last_name, gender, age) VALUES (3 , 'David', 'Sanford', 'male', 33);

-- Insert Person data
INSERT INTO person (id, full_name, email, demographic, gender, age) VALUES
(1, 'Armando', 'armando@ksu.com', 'American', 'M', 32),
(2, 'Alex', 'alex@ksu.com', 'American', 'F', 33),
(3, 'David', 'david@ksue.com', 'American', 'M', 29);

-- Insert Biometric data
INSERT INTO biometric (id, height, weight, waist_circumference, neck_circumference, body_fat_percentage, person_id) VALUES
(1, 170, 75, 85, 35, 18.5, 1),
(2, 170, 75, 85, 35, 18.5, 2),
(3, 170, 75, 85, 35, 18.5, 3);

-- Insert Target data
INSERT INTO target (id, daily_steps, weight_loss, weight_loss_percentage, person_id) VALUES
(1, 10000, 10, 5, 1),
(2, 10000, 11, 3, 2),
(3, 10000, 15, 4, 3);

-- Insert Activity data
INSERT INTO activity (id, steps, date, person_id) VALUES
(1, 8500, '2022-03-20', 1),
(2, 12000, '2022-03-20', 2),
(3,7000, '2022-03-20', 3),
(4,9500, '2022-03-19', 1),
(5,13000, '2022-03-19', 2),
(6,7500, '2022-03-19', 3),
(7,8000, '2022-03-18', 1),
(8,11000, '2022-03-18', 2),
(9,6500, '2022-03-18', 3),
(10,7500, '2022-03-17', 1),
(11,10000, '2022-03-17', 2),
(12,6000, '2022-03-17', 3),
(13,7000, '2022-03-16', 1),
(14,9000, '2022-03-16', 2),
(15,5500, '2022-03-16', 3);
