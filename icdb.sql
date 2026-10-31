DROP DATABASE IF EXISTS icdb;
CREATE DATABASE icdb;
USE icdb;

--
CREATE TABLE history
(
  id INT NOT NULL AUTO_INCREMENT,
  originalType TEXT,
  originalDegree DOUBLE,
  extreme BIT,
  convertedType TEXT,
  convertedDegree DOUBLE,
  PRIMARY KEY (id)
);

CREATE TABLE degree_type
(
  id INT NOT NULL AUTO_INCREMENT,
  type_name TEXT,
  PRIMARY KEY (id)
);

