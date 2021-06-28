CREATE TYPE branch_request AS ENUM('Java', 'C++', 'C#', 'JS');
CREATE TYPE level_request AS ENUM('Junior', 'Middle', 'Senior');
CREATE TABLE skills (
	id_skill SERIAL PRIMARY KEY NOT NULL,
	branch branch_request NOT NULL,
	level level_request NOT NULL);

CREATE TABLE companies (
	id_company SERIAL PRIMARY KEY NOT NULL,
	name_company VARCHAR(100) NOT NULL,
	city VARCHAR(100) NULL);

CREATE TABLE customers (
	id_customer SERIAL PRIMARY KEY NOT NULL,
	name_customer VARCHAR(100) NOT NULL,
	city VARCHAR(100) NULL);

CREATE TYPE sex_request AS ENUM('male', 'female');
CREATE TABLE developers (
	id_developers SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL,
	age INTEGER NULL,
	sex sex_request NOT NULL,
	id_company INTEGER NOT NULL,
	FOREIGN KEY(id_company) REFERENCES companies(id_company));

CREATE TABLE projects (
	id_project SERIAL PRIMARY KEY NOT NULL,
	name_project VARCHAR(100) NOT NULL,
	date_create DATE NOT NULL,
	id_customer int NOT NULL,
	id_company int NOT NULL,
	FOREIGN KEY(id_customer) REFERENCES customers (id_customer),
	FOREIGN KEY(id_company) REFERENCES companies (id_company));

CREATE TABLE developers_skills (
	id_developers int NOT NULL,
	id_skill int NOT NULL,
	PRIMARY KEY(id_developers, id_skill),
	FOREIGN KEY(id_developers) REFERENCES developers (id_developers),
	FOREIGN KEY(id_skill) REFERENCES skills (id_skill));

CREATE TABLE developers_projects (
	id_developers int NOT NULL,
	id_project int NOT NULL,
	PRIMARY KEY(id_developers, id_project),
	FOREIGN KEY(id_developers) REFERENCES developers (id_developers),
	FOREIGN KEY(id_project) REFERENCES projects (id_project));

ALTER TABLE developers ADD COLUMN salary INTEGER;
UPDATE developers SET salary = 2000 WHERE id_developers in (1, 3, 11);
UPDATE developers SET salary = 3000 WHERE id_developers in (5, 9, 10, 15);
UPDATE developers SET salary = 5000 WHERE id_developers in (2, 6, 8, 13, 14);
UPDATE developers SET salary = 4000 WHERE id_developers in (4, 7, 12);


ALTER TABLE projects ADD COLUMN cost INTEGER;
UPDATE projects SET cost = 13000 WHERE id_project=1;
UPDATE projects SET cost = 11000 WHERE id_project=2;
UPDATE projects SET cost = 12000 WHERE id_project=3;
UPDATE projects SET cost = 14000 WHERE id_project=4;
UPDATE projects SET cost = 15000 WHERE id_project=5;
UPDATE projects SET cost = 10000 WHERE id_project=6;
UPDATE projects SET cost = 9000 WHERE id_project=7;
UPDATE projects SET cost = 11000 WHERE id_project=8;
UPDATE projects SET cost = 13000 WHERE id_project=9;
