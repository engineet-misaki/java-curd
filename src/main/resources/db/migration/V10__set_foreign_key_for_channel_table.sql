ALTER TABLE messages
ADD CONSTRAINT `fk_department_id` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`id`);

ALTER TABLE channel_members
ADD CONSTRAINT `channel_members_ibfk_2` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`id`)
