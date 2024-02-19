CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(30) UNIQUE,
    email VARCHAR(60) UNIQUE NOT NULL,
    location VARCHAR(30),
    photo TEXT
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
    specialization varchar(250),
    start_education DATE,
    finish_education DATE,
    description TEXT,
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

CREATE TABLE IF NOT EXISTS education (
    id SERIAL PRIMARY KEY,
    name VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_education (
    user_id INT NOT NULL,
    education_id INT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_education FOREIGN KEY (education_id) REFERENCES education(id)
);









