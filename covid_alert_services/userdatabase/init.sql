CREATE TABLE users(
                      username varchar(50) NOT NULL PRIMARY KEY,
                      password varchar(100) NOT NULL,
                      enabled boolean NOT NULL DEFAULT FALSE,
                      first_name varchar(30),
                      last_name varchar(30),
                      email varchar(80),
                      phone_number varchar(20)
);

CREATE TABLE authorities(
                      authority_id SERIAL PRIMARY KEY,
                      username varchar(50) NOT NULL
                          REFERENCES users (username),
                      authority varchar(50) NOT NULL DEFAULT 'ROLE_USER'
);


INSERT INTO users(username,password,enabled,first_name,last_name,email,phone_number) VALUES
                                                                       ('JohnDoe','$2a$12$Kzl/8M5LWS9QgBkIn0DN7.Fic/8U1znZQk34ETnBCVbCImxkIz.LK',true,'John','Doe','john.doe@gmail.com','+15103754657'),
                                                                       ('TomRob','$2a$12$GMxFI99MxXWK3pOwhCtAYONOdPn9QmL0YAJYxsj3rvs0kClxoOm86',true,'Tom','Rob','tom.rob@yopmail.com','+41403257832');

INSERT INTO authorities(authority_id,username,authority) VALUES
                                                                    (1,'JohnDoe','USER'),
                                                                    (2,'JohnDoe','ADMIN'),
                                                                    (3,'TomRob','USER');