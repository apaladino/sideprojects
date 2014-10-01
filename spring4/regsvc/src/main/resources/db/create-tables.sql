-- ##############################################
-- Initial Setup
--
-- ##############################################



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
PRIMARY KEY(REGISTRANTID)
);