CREATE TABLE IF NOT EXISTS users_titles (
    user_id INT NOT NULL,
    title VARCHAR(150) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);