
ALTER TABLE authorities
ADD CONSTRAINT fk_authorities_users
FOREIGN KEY (username)
REFERENCES users(username)
ON UPDATE CASCADE
ON DELETE CASCADE;