
INSERT INTO tuulest.role (id, name) VALUES (default, 'admin');
INSERT INTO tuulest.role (id, name) VALUES (default, 'customer');

INSERT INTO tuulest."user" (id, role_id, username, password, status) VALUES (default, 1, 'admin', '123', 'A');

