create database if not exists favNumbers;

use favNumbers;

drop table if exists numbers;

create table numbers (
  id INT(250) NOT NULL AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  PRIMARY KEY(ID)
);