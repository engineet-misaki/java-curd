USE chat;

CREATE TABLE IF NOT EXISTS channels (
  id INT NOT NULL,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS messages (
  id CHAR(49) NOT NULL,
  channel_id INT NOT NULL,
  text TEXT NOT NULL,
  username VARCHAR(30) NOT NULL,
  timestamp TIMESTAMP NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_department_id
  FOREIGN KEY (channel_id)
  REFERENCES channels (id)
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users(
	username NVARCHAR(50),
	password NVARCHAR(500) NOT NULL,
	enabled boolean NOT NULL,
  PRIMARY KEY(username)
);

CREATE TABLE IF NOT EXISTS authorities (
	username NVARCHAR(50) not null,
	authority NVARCHAR(50) not null,
	CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username,authority);
