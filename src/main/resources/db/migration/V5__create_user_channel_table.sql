
CREATE TABLE IF NOT EXISTS channel_members (
  user_id INT NOT NULL,
  channel_id INT NOT NULL,
  PRIMARY KEY (user_id, channel_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (channel_id) REFERENCES channels(id),
  joined_at DATETIME DEFAULT CURRENT_TIMESTAMP
);