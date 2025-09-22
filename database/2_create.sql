-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-09-18 08:12:53.572

-- tables
-- Table: beach
CREATE TABLE beach (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    county_id int  NOT NULL,
    name varchar(255)  NOT NULL,
    description varchar(1000)  NULL,
    lat decimal(10,7)  NOT NULL,
    lng decimal(10,7)  NOT NULL,
    wind_direction_min int  NOT NULL,
    wind_direction_max int  NOT NULL,
    wind_speed_min decimal(4,1)  NOT NULL,
    wind_speed_max decimal(4,1)  NOT NULL,
    beach_status varchar(3)  NOT NULL,
    surf_status varchar(10) NULL,
    last_update timestamp  NULL,
    CONSTRAINT beach_pk PRIMARY KEY (id)
);

-- Table: beach_image
CREATE TABLE beach_image (
    id serial  NOT NULL,
    image_data bytea  NULL,
    beach_id int  NOT NULL,
    CONSTRAINT beach_image_pk PRIMARY KEY (id)
);

-- Table: comment
CREATE TABLE comment (
    id serial  NOT NULL,
    description varchar(255)  NOT NULL,
    beach_id int  NOT NULL,
    user_id int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT comment_pk PRIMARY KEY (id)
);

-- Table: config
CREATE TABLE config (
    id serial  NOT NULL,
    wind_speed_min decimal(4,1)  NOT NULL,
    wind_speed_max decimal(4,1)  NOT NULL,
    CONSTRAINT config_pk PRIMARY KEY (id)
);

-- Table: county
CREATE TABLE county (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    sequence int  NOT NULL,
    lat decimal(10,7)  NOT NULL,
    lng decimal(10,7)  NOT NULL,
    zoom_level int  NOT NULL,
    CONSTRAINT county_pk PRIMARY KEY (id)
);

-- Table: paid_report
CREATE TABLE paid_report (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    beach_id int  NOT NULL,
    surf_status varchar(10)  NOT NULL,
    last_update timestamp  NOT NULL,
    CONSTRAINT paid_report_pk PRIMARY KEY (id)
);

-- Table: profile
CREATE TABLE profile (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    email varchar(255)  NOT NULL,
    first_name varchar(255)  NOT NULL,
    last_name varchar(255)  NOT NULL,
    image_data bytea  NULL,
    CONSTRAINT profile_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role (
    id serial  NOT NULL,
    name varchar(10)  NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: subscription
CREATE TABLE subscription (
    id int  NOT NULL,
    user_id int  NOT NULL,
    date_from date  NOT NULL,
    date_to date  NOT NULL,
    CONSTRAINT subscription_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user" (
    id serial  NOT NULL,
    role_id int  NOT NULL,
    username varchar(255)  NOT NULL,
    password varchar(255)  NOT NULL,
    status varchar(1)  NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Table: user_beach_image
CREATE TABLE user_beach_image (
    id serial  NOT NULL,
    user_id int  NOT NULL,
    image_data bytea  NOT NULL,
    timestamp timestamp  NOT NULL,
    CONSTRAINT user_beach_image_pk PRIMARY KEY (id)
);

-- Table: weather
CREATE TABLE weather (
    id serial  NOT NULL,
    beach_id int  NOT NULL,
    wind_speed decimal(4,1)  NOT NULL,
    wind_direction int  NOT NULL,
    wind_gusts decimal(4,1)  NOT NULL,
    temperature decimal(4,1)  NOT NULL,
    precipitation decimal(4,1)  NOT NULL,
    timestamp timestamp  NOT NULL,
    surf_status varchar(10)  NOT NULL,
    type varchar(4)  NOT NULL,
    CONSTRAINT weather_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: beach_county (table: beach)
ALTER TABLE beach ADD CONSTRAINT beach_county
    FOREIGN KEY (county_id)
    REFERENCES county (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: beach_image_beach (table: beach_image)
ALTER TABLE beach_image ADD CONSTRAINT beach_image_beach
    FOREIGN KEY (beach_id)
    REFERENCES beach (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: beach_user (table: beach)
ALTER TABLE beach ADD CONSTRAINT beach_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: comment_beach (table: comment)
ALTER TABLE comment ADD CONSTRAINT comment_beach
    FOREIGN KEY (beach_id)
    REFERENCES beach (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: comment_user (table: comment)
ALTER TABLE comment ADD CONSTRAINT comment_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: paid_report_beach (table: paid_report)
ALTER TABLE paid_report ADD CONSTRAINT paid_report_beach
    FOREIGN KEY (beach_id)
    REFERENCES beach (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: profile_user (table: profile)
ALTER TABLE profile ADD CONSTRAINT profile_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: subscription_beach_user (table: paid_report)
ALTER TABLE paid_report ADD CONSTRAINT subscription_beach_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: subscription_user (table: subscription)
ALTER TABLE subscription ADD CONSTRAINT subscription_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: user_beach_image_user (table: user_beach_image)
ALTER TABLE user_beach_image ADD CONSTRAINT user_beach_image_user
    FOREIGN KEY (user_id)
    REFERENCES "user" (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: user_role (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_role
    FOREIGN KEY (role_id)
    REFERENCES role (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference: weather_beach (table: weather)
ALTER TABLE weather ADD CONSTRAINT weather_beach
    FOREIGN KEY (beach_id)
    REFERENCES beach (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- End of file.

