CREATE TABLE `users` (
                         `uid` VARCHAR(255) PRIMARY KEY,
                         `nickname` VARCHAR(255) NOT NULL UNIQUE,
                         `user_role` VARCHAR(50),
                         `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

insert into `users` (`uid`, `nickname`, `user_role`, `created_date`, `modified_date`)
values ('123-456-789', 'Test-User', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);