DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS score;

CREATE TABLE users (
	username	VARCHAR(30) NOT NULL,
	password	VARCHAR(30) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT p_users PRIMARY KEY (username);

CREATE TABLE score (
	id			bigint auto_increment,
	username	VARCHAR(30) NOT NULL,
	level		INT NOT NULL,
	score		INT NOT NULL
);

INSERT INTO users (username, password) VALUES('luke', '123');
INSERT INTO users (username, password) VALUES('vader', '456');
INSERT INTO users (username, password) VALUES('yoda', '789');