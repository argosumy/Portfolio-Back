CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(30) UNIQUE,
    email VARCHAR(60) UNIQUE NOT NULL,
    location VARCHAR(30),
    photo TEXT,
    cv_hidden BOOLEAN DEFAULT false
    );

CREATE TABLE IF NOT EXISTS user_security (
    user_id INT UNIQUE,
    password VARCHAR(150) NOT NULL,
    role VARCHAR(50) DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS experience (
    id SERIAL PRIMARY KEY,
    user_id INT,
    title VARCHAR(200),
    start_job DATE NOT NULL,
    finish_job DATE,
    description TEXT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS education (
    id SERIAL PRIMARY KEY,
    user_id INT,
    name_institute VARCHAR(300) NOT NULL,
    specialization VARCHAR(250),
    degree VARCHAR(100),
    start_education DATE,
    finish_education DATE,
    description TEXT,
    type VARCHAR(50),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS soft_skills (
    id SERIAL PRIMARY KEY,
    user_id INT,
    name VARCHAR(150),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS hard_skills (
    id SERIAL PRIMARY KEY,
    user_id INT,
    name VARCHAR(150),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS titles (
    id SERIAL PRIMARY KEY,
    user_id INT,
    title VARCHAR(150) UNIQUE NOT NULL,
    summary TEXT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);


