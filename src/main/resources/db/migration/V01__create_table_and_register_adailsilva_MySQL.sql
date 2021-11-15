CREATE DATABASE adailsilva;

USE adailsilva;

CREATE TABLE model (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO model (name, date) 
	VALUES ('AdailSilva', sysdate());

SELECT * FROM model;