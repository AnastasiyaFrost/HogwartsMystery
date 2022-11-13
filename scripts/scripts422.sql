CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    car brand VARCHAR,
    car model VARCHAR,
    price NUMERIC(9,2));

CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    age INT,
    have_the_right_to_drive_car BOOLEAN,
    car_id TEXT REFERENCES cars(id));