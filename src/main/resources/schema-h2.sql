CREATE TABLE IF NOT EXISTS `User` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `registered_at` DATETIME NULL,
  `test` DATETIME NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Board` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Board_User`
    FOREIGN KEY (`User_id`)
    REFERENCES `User` (`id`)
);

CREATE TABLE IF NOT EXISTS `Post` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `contents` VARCHAR(1000) NULL,
  `createdAt` DATETIME NULL,
  `updatedAt` DATETIME NULL,
  `User_id` INT NOT NULL,
  `Board_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Post_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `User` (`id`),
  CONSTRAINT `fk_Post_Board1`
    FOREIGN KEY (`Board_id`)
    REFERENCES `Board` (`id`)
);

CREATE TABLE IF NOT EXISTS `Reply` (
  `id` INT NOT NULL,
  `contents` VARCHAR(1000) NULL,
  `createdAt` DATETIME NULL,
  `updatedAt` DATETIME NULL,
  `Post_id` INT NOT NULL,
  `User_id` INT NOT NULL,
  `Reply_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Reply_Post1`
    FOREIGN KEY (`Post_id`)
    REFERENCES `Post` (`id`),
  CONSTRAINT `fk_Reply_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `User` (`id`),
  CONSTRAINT `fk_Reply_Reply1`
    FOREIGN KEY (`Reply_id`)
    REFERENCES `Reply` (`id`)
);
