CREATE TABLE `leave` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `leave_day` DATE NOT NULL,
  `from` FLOAT NOT NULL,
  `to` FLOAT NOT NULL,
  `comment` VARCHAR(2000) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_vietnamese_ci' NULL,
  `approve` BOOLEAN,
  `approve_user_id` INT,
  `approve_comment` VARCHAR(2000) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_vietnamese_ci' NULL,
  `is_delete` int,
  PRIMARY KEY (`id`));
