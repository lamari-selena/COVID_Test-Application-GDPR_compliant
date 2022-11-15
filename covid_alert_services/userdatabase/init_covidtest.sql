CREATE TABLE covidtests(
                            covidtest_id serial NOT NULL PRIMARY KEY,
                            covidtest_type varchar(30) NOT NULL,
                            covidtest_result varchar(30) NOT NULL,
                            covidtest_valid_duration int NOT NULL,
                            covidtest_date timestamp NOT NULL,
                            user_id varchar(50) NOT NULL
);



INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(1001,'PCR','négatif',72,'2004-05-23T14:25:10','1');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(1002,'PCR','négatif',72,'2004-05-23T14:25:10','2');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(1003,'Antigénique','négatif',48,'2004-05-23T14:25:10','2');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(1004,'Antigénique','négatif',48,'2004-05-23T14:25:10','1');
INSERT INTO covidtests(covidtest_id,covidtest_type,covidtest_result,covidtest_valid_duration,covidtest_date,user_id) VALUES(1005,'Antigénique','positif',48,'2004-05-23T14:25:10','3');