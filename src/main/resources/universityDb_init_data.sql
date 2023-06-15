USE universityDb;

INSERT INTO program(id, program_name) VALUES (1, 'Programming');
INSERT INTO program(id, program_name) VALUES (2, 'Frontend development');
INSERT INTO program(id, program_name) VALUES (3, 'Cyber security');
INSERT INTO program(id, program_name) VALUES (4, 'E-business');

INSERT INTO role(id, role_name) VALUES (1, 'Student');
INSERT INTO role(id, role_name) VALUES (2, 'Teacher');
INSERT INTO role(id, role_name) VALUES (3, 'Guest');

INSERT INTO student(id, first_name, role_id, program_id) VALUES (1, 'John', 1, 1);
INSERT INTO student(id, first_name, role_id, program_id) VALUES (2, 'Jane', 1, 2);
INSERT INTO student(id, first_name, role_id, program_id) VALUES (3, 'Jack', 1, 3);
INSERT INTO student(id, first_name, role_id, program_id) VALUES (4, 'Jill', 1, 4);

INSERT INTO staff(id, first_name, role_id) VALUES (1, 'Adam', 2);
INSERT INTO staff(id, first_name, role_id) VALUES (2, 'Alice', 2);
INSERT INTO staff(id, first_name, role_id) VALUES (3, 'Allistar', 2);
INSERT INTO staff(id, first_name, role_id) VALUES (4, 'Amanda', 2);

INSERT INTO guest(id, first_name, role_id) VALUES (1, 'Graham', 3);
INSERT INTO guest(id, first_name, role_id) VALUES (2, 'Gloria', 3);
INSERT INTO guest(id, first_name, role_id) VALUES (3, 'Gordon', 3);
INSERT INTO guest(id, first_name, role_id) VALUES (4, 'Gina', 3);


