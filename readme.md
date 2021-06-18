# Project name
Wild track.
## Author
Quienzy Ong'eye
### Set Up
To recreate this app follow these steps:
* CREATE DATABASE wild_track;
* \c wildlife_tracker;
* CREATE TABLE animals (id serial PRIMARY KEY, name varchar, type varchar, age varchar, health varchar, location varchar);
* CREATE TABLE endangered (id serial PRIMARY KEY, name varchar, health varchar, age varchar);
* CREATE TABLE sightings (animals_id int, location varchar, rangername varchar, animal_type varchar);
* CREATE TABLE rangers (id serial PRIMARY KEY, name varchar, tag int);
* CREATE DATABASE wild_track_test WITH TEMPLATE wild_track;
## Technologies used
Java
Spark
JUnit-4
Handlebars
Gradle
### Live Site
View [LIVE] (https://github.com/Queen-01/wild-track.git)

### License
This project is under the  [MIT](License) license
