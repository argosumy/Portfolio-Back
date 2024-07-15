DO
$do$
    BEGIN
        --INSERT users
        IF NOT EXISTS (SELECT * FROM users WHERE email = 'argosumy@gmail.com') THEN
            INSERT INTO users(first_name, last_name, email) VALUES ('Valerii','Lozynskyi','loz.valerii@gmail.com');
            INSERT INTO user_security(user_id, password, role) VALUES (1, '$2a$08$xAi3bVoUxcCVRl2vnC1IR.bAXI/G0cJp1C4VlymP98qDqMqleqr4K','OWNER');
        END IF;
        IF NOT EXISTS (SELECT * FROM users WHERE email = 'notexist@ua.fm') THEN
            INSERT INTO users(first_name, last_name, email) VALUES ('Liubov', 'Romanova', 'notexist@ua.fm');
            INSERT INTO user_security(user_id, password, role) VALUES (2, '$2a$08$xAi3bVoUxcCVRl2vnC1IR.bAXI/G0cJp1C4VlymP98qDqMqleqr4K','USER');
        END IF;
        IF NOT EXISTS (SELECT * FROM users WHERE email = 'notexist@tol.com') THEN
            INSERT INTO users(first_name, last_name, email) VALUES ('Michael', 'Barnes', 'notexist@best.com');
        END IF;
        --INSERT education
        IF NOT EXISTS (SELECT * FROM education WHERE user_id = '1') THEN
            INSERT INTO education(user_id, name_institute, specialization, start_education, finish_education, type)
            VALUES (1, 'Kharkov University of Internal Affairs', 'Information systems in management', '1995-01-01', '2000-06-25', 'EDUCATION');
            INSERT INTO education(user_id, specialization, finish_education, type)
            VALUES (1, 'Basics of Programming with HTML, CSS, JavaScript', '2024-01-26', 'TRAINING');
            INSERT INTO education(user_id, specialization, finish_education, type)
            VALUES (1, 'Java course SPD University', '2022-06-03', 'TRAINING');
            INSERT INTO education(user_id, specialization, finish_education, type)
            VALUES (1, 'Internship SPD University', '2021-05-01', 'TRAINING');
            INSERT INTO education(user_id, specialization, start_education, finish_education, type)
            VALUES (1, 'Java course Netcracker', '2019-09-01', '2020-05-01', 'TRAINING');
        END IF;

        IF NOT EXISTS (SELECT * FROM education WHERE user_id = '2') THEN
            INSERT INTO education(user_id, name_institute, specialization, start_education, finish_education, type)
            VALUES (2, 'Sumy National Agrarian University', 'Management', '2010-08-01', '2015-05-25', 'EDUCATION');
        END IF;
        IF NOT EXISTS (SELECT * FROM education WHERE user_id = '3') THEN
            INSERT INTO education(user_id, name_institute, start_education, finish_education, type)
            VALUES (3, 'University of Maryland School of Law', '2005-09-01', '2010-06-25', 'EDUCATION');
        END IF;
        --INSERT experience
        IF NOT EXISTS (SELECT * FROM experience WHERE id = 1) THEN
            INSERT INTO experience(user_id, title, start_job, finish_job, description)
            VALUES (1, 'Java developer - DataOX', '2021-06-06', null, 'Attorneys Scraper Project
The main goal of the project is to form a unified database of lawfirm profiles grouped by region. The profile of attorney hasemployment history, practical areas of competence, the history ofcases, received honors and more. The data is collected and used forstatistical and analytical purposes.
Technology Stack:
Java Stream API, JSOUP, Java RegEx, OkHttp3,Selenium, Gson,
CSS, HTML, Apache POI, JUnit, Postman.');
        END IF;
        IF NOT EXISTS (SELECT * FROM experience WHERE id = 2) THEN
                INSERT INTO experience(user_id, title, start_job, finish_job, description)
                VALUES (1, 'Java Developer - SPD', '2021-03-01', '2021-06-30', 'Beauty Services Booking System.
The platform allows customers to choose a salon, beauty service,useful time slot and schedule an appointment. The platform sendsreminders by email. Owners of salons can create a business profileand add employees to the booking platform, as well as promotetheir services.
Technology Stack:
Java, Spring Boot, PostgreSQL, Minio, AWS S3,Flyway, Swagger, Docker Tasks/features: - project configuration
- module of authentication/authorization - CRUD operations for users - file storage - service of notifications - unit tests');
        END IF;
        --INSERT skills
        IF NOT EXISTS (SELECT * FROM skills WHERE user_id = 1) THEN
            INSERT INTO skills(user_id, name, type) VALUES (1, 'Java, Spring Boot, PostgreSQL, JDBC, JavaScript, HTML, CSS, Docker, Swagger, Minio, Flyaway', 'HARD');
            INSERT INTO skills(user_id, name, type) VALUES (1, 'Eeasy learning, Stress-resistant, Self-organized, Open for challenges, Positive attitude, Excellent communication skills', 'SOFT');
        END IF;
        --INSERT title
        IF NOT EXISTS (SELECT * FROM titles WHERE user_id = 1) THEN
            INSERT INTO titles(user_id, title) VALUES (1, 'Java Developer');
        END IF;
    END
$do$






