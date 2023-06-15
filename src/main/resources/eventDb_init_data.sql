use eventDb;

INSERT INTO event(id, name, description) VALUES (1, 'Graduation', 'Graduation Ceremony');
INSERT INTO event(id, name, description) VALUES (2, 'OOP exam', 'Programming exam for 1st year students');
INSERT INTO event(id, name, description) VALUES (3, 'Student showcase', 'Showcase of student projects');
INSERT INTO event(id, name, description) VALUES (4, 'BBQ in park', 'BBQ in the park for students and staff');

INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (1, 1, 1, 1);
INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (2, 1, 2, 2);
INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (3, 1, 3, 2);
INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (4, 1, 1, 3);
INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (5, 1, 2, 3);

INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (6, 2, 1, 1);
INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (7, 2, 2, 2);
INSERT INTO event_registration(id, event_id, attendee_id, attendee_role_id) VALUES (8, 2, 3, 1);