CREATE TABLE users (
    username VARCHAR(225) PRIMARY KEY,
    password VARCHAR(225) NOT NULL UNIQUE
);

CREATE TABLE notes (
    id UUID PRIMARY KEY,
    title VARCHAR(225),
    content TEXT,
    user_id VARCHAR(225),
    FOREIGN KEY(user_id) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE
);