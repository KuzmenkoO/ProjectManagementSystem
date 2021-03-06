INSERT INTO skills (branch, level) VALUES ('Java', 'Junior');
INSERT INTO skills (branch, level) VALUES ('Java', 'Middle');
INSERT INTO skills (branch, level) VALUES ('Java', 'Senior');
INSERT INTO skills (branch, level) VALUES ('C++', 'Junior');
INSERT INTO skills (branch, level) VALUES ('C++', 'Middle');
INSERT INTO skills (branch, level) VALUES ('C++', 'Senior');
INSERT INTO skills (branch, level) VALUES ('C#', 'Junior');
INSERT INTO skills (branch, level) VALUES ('C#', 'Middle');
INSERT INTO skills (branch, level) VALUES ('C#', 'Senior');
INSERT INTO skills (branch, level) VALUES ('JS', 'Junior');
INSERT INTO skills (branch, level) VALUES ('JS', 'Middle');
INSERT INTO skills (branch, level) VALUES ('JS', 'Senior');

INSERT INTO companies (name_company, city) VALUES ('EPAM', 'Kiev');
INSERT INTO companies (name_company, city) VALUES ('SoftServe', 'Kiev');
INSERT INTO companies (name_company, city) VALUES ('GlobalLogic', 'Kiev');

INSERT INTO customers (name_customer, city) VALUES ('Panasonic Appliance Air-Conditioning', 'Kadoma');
INSERT INTO customers (name_customer, city) VALUES ('Lufthansa Embraer', 'Frankfurt');
INSERT INTO customers (name_customer, city) VALUES ('McDonalds', 'Kiev');
INSERT INTO customers (name_customer, city) VALUES ('Alfa-Bank', 'Kiev');

INSERT INTO developers (name, age, sex, id_company) VALUES ('Oleg', 28, 'male', 1);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Steven', 35, 'male', 1);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Diana', 25, 'female', 1);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Ira', 30, 'female', 1);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Olga', 28, 'female', 1);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Oleksandr', 36, 'male', 2);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Dima', 41, 'male', 2);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Kira', 37, 'female', 2);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Slava', 21, 'male', 2);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Den', 34, 'male', 2);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Karina', 28, 'female', 3);
INSERT INTO developers (name, age, sex, id_company) VALUES ('lu', 40, 'male', 3);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Oleg', 49, 'male', 3);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Roma', 59, 'male', 3);
INSERT INTO developers (name, age, sex, id_company) VALUES ('Sem', 20, 'male', 3);

INSERT INTO developers_skills (id_developers, id_skill) VALUES (1, 1);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (7, 1);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (9, 1);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (4, 2);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (10, 2);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (13, 2);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (2, 3);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (6, 3);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (14, 3);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (5, 4);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (6, 4);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (1, 5);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (15, 5);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (8, 6);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (13, 6);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (10, 7);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (15, 7);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (8, 8);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (7, 9);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (12, 9);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (14, 9);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (1, 10);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (2, 10);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (9, 10);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (3, 11);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (11, 11);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (4, 12);
INSERT INTO developers_skills (id_developers, id_skill) VALUES (5, 12);

INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('Panasonic 1', '2018-01-15', 1, 1);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('Panasonic 2', '2016-05-11', 1, 3);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('Lufthansa 1', '2017-07-15', 2, 2);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('Lufthansa 2', '2020-01-17', 2, 3);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('McDonalds 1', '2019-01-25', 3, 2);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('McDonalds 2', '2018-01-11', 3, 1);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('Alfa-Bank 1', '2016-08-21', 4, 1);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('Alfa-Bank 2', '2018-01-15', 4, 2);
INSERT INTO projects (name_project, date_create, id_customer, id_company) VALUES ('Alfa-Bank 3', '2017-09-30', 4, 3);

INSERT INTO developers_projects (id_developers, id_project) VALUES (1, 1);
INSERT INTO developers_projects (id_developers, id_project) VALUES (2, 1);
INSERT INTO developers_projects (id_developers, id_project) VALUES (3, 1);
INSERT INTO developers_projects (id_developers, id_project) VALUES (4, 1);
INSERT INTO developers_projects (id_developers, id_project) VALUES (11, 2);
INSERT INTO developers_projects (id_developers, id_project) VALUES (12, 2);
INSERT INTO developers_projects (id_developers, id_project) VALUES (13, 2);
INSERT INTO developers_projects (id_developers, id_project) VALUES (6, 3);
INSERT INTO developers_projects (id_developers, id_project) VALUES (7, 3);
INSERT INTO developers_projects (id_developers, id_project) VALUES (8, 3);
INSERT INTO developers_projects (id_developers, id_project) VALUES (11, 4);
INSERT INTO developers_projects (id_developers, id_project) VALUES (12, 4);
INSERT INTO developers_projects (id_developers, id_project) VALUES (14, 4);
INSERT INTO developers_projects (id_developers, id_project) VALUES (15, 4);
INSERT INTO developers_projects (id_developers, id_project) VALUES (6, 5);
INSERT INTO developers_projects (id_developers, id_project) VALUES (7, 5);
INSERT INTO developers_projects (id_developers, id_project) VALUES (9, 5);
INSERT INTO developers_projects (id_developers, id_project) VALUES (10, 5);
INSERT INTO developers_projects (id_developers, id_project) VALUES (1, 6);
INSERT INTO developers_projects (id_developers, id_project) VALUES (2, 6);
INSERT INTO developers_projects (id_developers, id_project) VALUES (5, 6);
INSERT INTO developers_projects (id_developers, id_project) VALUES (3, 7);
INSERT INTO developers_projects (id_developers, id_project) VALUES (4, 7);
INSERT INTO developers_projects (id_developers, id_project) VALUES (5, 7);
INSERT INTO developers_projects (id_developers, id_project) VALUES (8, 8);
INSERT INTO developers_projects (id_developers, id_project) VALUES (9, 8);
INSERT INTO developers_projects (id_developers, id_project) VALUES (10, 8);
INSERT INTO developers_projects (id_developers, id_project) VALUES (13, 9);
INSERT INTO developers_projects (id_developers, id_project) VALUES (14, 9);
INSERT INTO developers_projects (id_developers, id_project) VALUES (15, 9);