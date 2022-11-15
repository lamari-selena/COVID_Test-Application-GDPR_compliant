
CREATE TABLE locations(
                          location_id serial PRIMARY KEY,
                          latitude double precision NOT NULL,
                          longitude double precision NOT NULL,
                          location_date timestamp with time zone NOT NULL,
                          user_id varchar(255)


);



INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1000,43.63746472422702,3.8409670228559136,now(),'un');
INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1001,46,46,now(),'deux');
INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1002,99,99,now(),'trois');
INSERT INTO locations(location_id,latitude,longitude,location_date,user_id) VALUES(1003,46,46,'2019-11-15T20:48:08.831+00:00','quatre');



