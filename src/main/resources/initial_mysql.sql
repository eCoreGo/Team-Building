DROP DATABASE IF EXISTS team_building;
CREATE DATABASE team_building;
USE team_building;

CREATE TABLE app_user (
  id            			  INT			        AUTO_INCREMENT,
  name              	  VARCHAR(200)  	NOT NULL,
  phone                 CHAR(15)  	    NOT NULL,
  balance               DOUBLE          NOT NULL,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;