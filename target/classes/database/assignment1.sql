create database if not exists `assignment` /*!40100 DEFAULT CHARACTER SET latin1 */;
use assignment;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `date_of_birth` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


INSERT INTO user
(id, username, password, name, phone, email, address, date_of_birth)
VALUES(1, 'thinhvn', 'd8f9f3c7e0933b1c51b161f6ae4244c9', 'vu ngoc thinh','0972264970', 'vungocthinhbk@gmail.com', 'Nam Dinh', '27/06/1996');