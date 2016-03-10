INSERT INTO role(ID, role) VALUES (1, 'USER');
INSERT INTO role(ID, role) VALUES (2, 'ADMIN');
insert into user( id ,email ,firstName ,lastName ,password ) values ( 1, 'admin@test.com', 'Admin', 'Istrator', 'bXW7XpdiR775awTTzf4q6XkZ4uShMkfwffLMGPzoTTc=');
insert into user_role(user_id ,roles_id ) values (1, 1);
insert into user_role(user_id ,roles_id ) values (1, 2);