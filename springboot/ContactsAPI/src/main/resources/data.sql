INSERT INTO PERSON(FIRST_NAME, LAST_NAME) VALUES
('Andy', 'Paladino'),
('Homer', 'Simpson'),
('Marge', 'Simpson'),
('Maggie', 'Simpson'),
('Bart', 'Simpson'),
('Lisa', 'Simpson'),
('Grandpa', 'Simpson'),
('Peter', 'Griffin'),
('Lois', 'Griffin'),
('Brian', 'Griffin'),
('Meg', 'Griffin'),
('Stewie', 'Griffin');

INSERT INTO ADDRESS(ID, STREET, CITY, STATE, POSTAL_CODE) VALUES
(1,'100 someplace lane', 'Springfield', 'MA', '04111');

INSERT INTO PERSON_ADDRESS_MAP(PERSON_ID, ADDRESS_ID) VALUES( 1, 1);