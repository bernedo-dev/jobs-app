-- Script para crear la base de datos empleosdb (MySQL 8.0)

DROP TABLE IF EXISTS `Categories`;
CREATE TABLE `Categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Profiles`;
CREATE TABLE `Profiles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `profile` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `createdAt` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Jobs`;
CREATE TABLE `Jobs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` text NOT NULL,
  `publicationDate` date NOT NULL,
  `salary` double NOT NULL,
  `status` enum('Creada','Aprobada','Eliminada') NOT NULL,
  `featured` int NOT NULL,
  `image` varchar(250) NOT NULL,
  `details` text,
  `categoryId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Jobs_Categories1_idx` (`categoryId`),
  CONSTRAINT `fk_Jobs_Categories1` FOREIGN KEY (`categoryId`) REFERENCES `Categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `Requests`;
CREATE TABLE `Requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createdAt` date NOT NULL,
  `file` varchar(250) NOT NULL,
  `comments` text,
  `jobId` int NOT NULL,
  `userId` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Job_User_UNIQUE` (`jobId`,`userId`),
  KEY `fk_Requests_Jobs1_idx` (`jobId`),
  KEY `fk_Requests_Users1_idx` (`userId`),
  CONSTRAINT `fk_Requests_Users1` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `fk_Requests_Jobs1` FOREIGN KEY (`jobId`) REFERENCES `Jobs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `UserProfile`;
CREATE TABLE `UserProfile` (
  `userId` int NOT NULL,
  `profileId` int NOT NULL,
  UNIQUE KEY `User_Profile_UNIQUE` (`userId`,`profileId`),
  KEY `fk_Users1_idx` (`userId`),
  KEY `fk_Profiles1_idx` (`profileId`),
  CONSTRAINT `fk_Users1` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`),
  CONSTRAINT `fk_Profiles1` FOREIGN KEY (`profileId`) REFERENCES `Profiles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

