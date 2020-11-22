
CREATE TABLE ROLES(
    row_id NUMBER PRIMARY KEY,
    granted_role TEXT,
    user_name TEXT
    );

CREATE TABLE USER(
  user_ID NUMBER PRIMARY KEY,
  user_name TEXT,
  first_name TEXT,
  last_name TEXT
 );

CREATE TABLE USER_DEFAULTS(
  user_id NUMBER PRIMARY KEY,
  user_name TEXT,
  location_id TEXT
  );

        
insert into USER(user_id, user_name, first_name, last_name) 
	VALUES(1, 'ANDREWSJ', 'JAMES', 'ANDREWS');

insert into USER(user_id, user_name, first_name, last_name) 
	VALUES(2, 'SALLY', 'ANDREWSJ', 'YATES');


insert into ROLES (row_id, granted_role, user_name) 
	VALUES (1, 'ROLE_FRONT_DESK', 'ANDREWSJ');

insert into ROLES (row_id, granted_role, user_name) 
	VALUES (2, 'ROLE_CLERK', 'ANDREWSJ');

insert into ROLES (row_id, granted_role, user_name) 
	VALUES (3, 'ROLE_SALES', 'ANDREWSJ');

insert into ROLES (row_id, granted_role, user_name) 
	VALUES (4, 'ROLE_FRONT_DESK', 'YATESS');

insert into ROLES (row_id, granted_role, user_name) 
	VALUES (5, 'ROLE_CLERK', 'YATESS');

insert into ROLES (row_id, granted_role, user_name) 
	VALUES (6, 'ROLE_SALES', 'YATESS');

insert into ROLES (row_id, granted_role, user_name) 
	VALUES (7, 'ROLE_MANAGER', 'YATESS');


insert into USER_DEFAULTS  (user_id, user_name, location_id) 
	VALUES(1, 'ANDREWSJ', 'MAIN');
insert into USER_DEFAULTS (user_id, user_name, location_id)
	VALUES(2, 'YATESS', 'MAIN');

