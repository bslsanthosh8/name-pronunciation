CREATE TABLE IF NOT EXISTS EMPLOYEE1 (
    id NUMBER(10) NOT NULL,
    name VARCHAR2(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS  ENTITLEMENT (
    entitlement VARCHAR2(50) NOT NULL,
    entitlement_desc VARCHAR2(250),
    created_by VARCHAR2(7) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL,
    modified_timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY(entitlement)
);

CREATE TABLE IF NOT EXISTS  EMPLOYEE (
    uid VARCHAR2(7) NOT NULL,
    emp_id VARCHAR2(7) NOT NULL,
    first_name VARCHAR2(50) NOT NULL,
    middle_name VARCHAR2(50),
    last_name VARCHAR2(50),
    preferred_name VARCHAR2(50),
    is_preferred_name_chosen CHAR(1),
    email_id VARCHAR2(50),
    entitlement VARCHAR2 NOT NULL DEFAULT 'EMPLOYEE',
    created_by VARCHAR2(7) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY(uid),
    FOREIGN KEY (entitlement) REFERENCES ENTITLEMENT(entitlement)

);


CREATE TABLE IF NOT EXISTS  NAME_PRONUNCIATION (
    uid VARCHAR2(7) NOT NULL,
    pronunciation_sound BLOB,
    format VARCHAR2(10) NOT NULL DEFAULT 'MP3',
    created_by VARCHAR2(7) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL,
    modified_timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY(uid)

);
CREATE TABLE IF NOT EXISTS  EMPLOYEE_AVATAR (
id INTEGER,
uid VARCHAR2(50),
image BLOB,
 created_by VARCHAR2(7) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL,
    modified_timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (uid) REFERENCES EMPLOYEE(uid)
);

CREATE TABLE IF NOT EXISTS  ENROLLED_APPLICATION (
    app_id INTEGER NOT NULL,
    app_token VARCHAR2(50),
    app_desc VARCHAR2(250),
    created_by VARCHAR2(7) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL,
    modified_timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY(app_id)
);

CREATE TABLE IF NOT EXISTS  AUDIT_LOG (
    id INTEGER,
    uid VARCHAR2 (7) NOT NULL,
    app_id INTEGER,
    requested_name VARCHAR2(250),
    created_timestamp TIMESTAMP NOT NULL,
    service_name VARCHAR2(100),

    PRIMARY KEY(id)
);

CREATE SEQUENCE IF NOT EXISTS audit_log_sequence
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS enrollment_application_sequence
  START WITH 1
  INCREMENT BY 1;

  CREATE SEQUENCE IF NOT EXISTS name_pronunciation_sequence
    START WITH 1
    INCREMENT BY 1;

 create SEQUENCE IF NOT EXISTS  employee_avatar_sequence
 START WITH 1
 INCREMENT BY 1;

 CREATE INDEX IF NOT EXISTS idx_preferred_name on EMPLOYEE(preferred_name);
 CREATE INDEX IF NOT EXISTS idx_uid on EMPLOYEE(uid);
 CREATE INDEX IF NOT EXISTS idx_first_name on EMPLOYEE(first_name);
 CREATE INDEX IF NOT EXISTS idx_last_name on EMPLOYEE(last_name);
 CREATE INDEX IF NOT EXISTS idx_middle_name on EMPLOYEE(middle_name);
 CREATE INDEX IF NOT EXISTS idx_email_id on EMPLOYEE(email_id);


