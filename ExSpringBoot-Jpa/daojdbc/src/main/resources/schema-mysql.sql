DROP DATABASE IF EXISTS spring;
CREATE DATABASE spring;
USE spring;

CREATE TABLE IF NOT EXISTS product(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255),
  `price` bigint(20),
  PRIMARY KEY(`id`)
)ENGINE= InnoDB
DEFAULT CHARACTER SET = utf8;