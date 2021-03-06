-- ##############################################
-- Initial Setup
--
-- ##############################################
SET DATABASE UNIQUE NAME HSQLDB2467A82B33


drop table IF EXISTS REGISTRANTS;
drop table IF EXISTS LINKEDINPROFILE;
drop table IF EXISTS LINKEDINCOMPANYPROFILE;
drop table IF EXISTS FACEBOOKPROFILE;

--
-- FACEBOOKPROFILE
--
CREATE TABLE IF NOT EXISTS FACEBOOKPROFILE(
    FBPROFILEID BIGINT GENERATED BY DEFAULT AS IDENTITY
         (START WITH 1, INCREMENT BY 1) NOT NULL,
    FIRSTNAME VARCHAR(50) NOT NULL,
    LASTNAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(128) NOT NULL,
    PICTUREURL VARCHAR(156),
    FBLINK VARCHAR(156),
    LOCALE VARCHAR(28),
    TIMEZONE VARCHAR(28),
    AGERANGE VARCHAR(28),
    CREATETIME DATE,
    PRIMARY KEY(FBPROFILEID)
);

--
-- REGISTRANTS
--
CREATE TABLE IF NOT EXISTS REGISTRANTS(
REGISTRANTID BIGINT GENERATED BY DEFAULT AS IDENTITY
                                         (START WITH 1, INCREMENT BY 1) NOT NULL,
FIRSTNAME VARCHAR(50) NOT NULL,
LASTNAME VARCHAR(50) NOT NULL,
EMAIL VARCHAR(128) NOT NULL,
CREATETIME DATE,
LN_PROFILEID BIGINT,
FB_PROFILEID BIGINT,
PRIMARY KEY(REGISTRANTID),
FOREIGN KEY (FB_PROFILEID) REFERENCES FACEBOOKPROFILE(FBPROFILEID)
);

--
-- LINKEDINPROFILE
--
CREATE TABLE IF NOT EXISTS LINKEDINPROFILE(
    LNPROFILEID BIGINT GENERATED BY DEFAULT AS IDENTITY
         (START WITH 1, INCREMENT BY 1) NOT NULL,
    FIRSTNAME VARCHAR(50) NOT NULL,
    LASTNAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(128) NOT NULL,
    CREATETIME DATE,
    PRIMARY KEY(LNPROFILEID)
);



--
-- LINKEDINCOMPANYPROFILE
--
CREATE TABLE IF NOT EXISTS LINKEDINCOMPANYPROFILE(
    COMPANYPROFILEID BIGINT GENERATED BY DEFAULT AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    PROFILEID BIGINT,
    NAME VARCHAR(100) NOT NULL,
    TITLE VARCHAR(50) NOT NULL,
    SUMMARY VARCHAR(256),
    INDUSTRY VARCHAR(50),
    COMPANYSIZE VARCHAR(28),
    TICKER VARCHAR(28),
    TYPE VARCHAR(50),
    ISCURRENT BOOLEAN,
    STARTDATE DATE,
    PRIMARY KEY(COMPANYPROFILEID)
);

ALTER TABLE LINKEDINCOMPANYPROFILE ADD FOREIGN KEY(PROFILEID)
REFERENCES LINKEDINPROFILE(LNPROFILEID);


ALTER TABLE REGISTRANTS ADD FOREIGN KEY (LN_PROFILEID)
REFERENCES LINKEDINPROFILE(LNPROFILEID);

--
-- ORGANIZERS
--
CREATE TABLE IF NOT EXISTS ORGANIZERS(
    ORGANIZERID BIGINT GENERATED BY DEFAULT AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
    FIRSTNAME VARCHAR(50),
    LASTNAME VARCHAR(50),
    EMAIL VARCHAR(128),
    PRIMARY KEY (ORGANIZERID)
);

--
-- EVENTS
--
CREATE TABLE IF NOT EXISTS EVENTS(
     EVENTID BIGINT GENERATED BY DEFAULT AS IDENTITY
        (START WITH 1, INCREMENT BY 1) NOT NULL,
     ORGANIZERID BIGINT,
     TITLE VARCHAR(256),
     DESCRIPTION VARCHAR(1028),
     STARTDATE DATE,
     ENDDATE DATE,
     ISPUBLIC BOOLEAN,
     PRIMARY KEY (EVENTID),
     FOREIGN KEY(ORGANIZERID) REFERENCES ORGANIZERS(ORGANIZERID)
);

--
-- EVENT_REGISTRANT
--
CREATE TABLE IF NOT EXISTS EVENTS_REGISTRANTS(
    EVENTID BIGINT NOT NULL,
    REGISTRANTID BIGINT NOT NULL,
    FOREIGN KEY(EVENTID) REFERENCES EVENTS(EVENTID),
    FOREIGN KEY(REGISTRANTID) REFERENCES REGISTRANTS(REGISTRANTID)
);
