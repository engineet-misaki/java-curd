ALTER TABLE messages
ADD COLUMN user_id INT NOT NULL,
DROP COLUMN username,
ADD CONSTRAINT `fk_messages_user`
  FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);
