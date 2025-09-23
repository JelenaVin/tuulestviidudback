INSERT INTO tuulest.county (id, name, sequence, lat, lng, zoom_level) VALUES (default, 'Harju maakond', 100, 59.3558217, 24.8872283, 9);
INSERT INTO tuulest.county (id, name, sequence, lat, lng, zoom_level) VALUES (default, 'Hiiu maakond', 200, 58.9030207, 22.6147041, 9);
INSERT INTO tuulest.county (id, name, sequence, lat, lng, zoom_level) VALUES (default, 'Ida-Viru maakond', 300, 59.3031886, 27.3531442, 9);
INSERT INTO tuulest.county (id, name, sequence, lat, lng, zoom_level) VALUES (default, 'Lääne maakond', 400, 59.0044683, 23.5944264, 9);
INSERT INTO tuulest.county (id, name, sequence, lat, lng, zoom_level) VALUES (default, 'Lääne-Viru maakond', 500, 59.4183856, 26.3430591, 9);
INSERT INTO tuulest.county (id, name, sequence, lat, lng, zoom_level) VALUES (default, 'Pärnu maakond', 600, 58.3881517, 24.4890331, 9);
INSERT INTO tuulest.county (id, name, sequence, lat, lng, zoom_level) VALUES (default, 'Saare maakond', 700, 58.4149387, 22.6112131, 9);

INSERT INTO tuulest.role (id, name) VALUES (1, 'admin');
INSERT INTO tuulest.role (id, name) VALUES (2, 'customer');

INSERT INTO tuulest."user" (id, role_id, username, password, status) VALUES (default, 1, 'admin', '123', 'A');
INSERT INTO tuulest."user" (id, role_id, username, password, status) VALUES (default, 2, 'customer', '123', 'A');


INSERT INTO tuulest.beach (id, user_id, county_id, name, description, lat, lng, wind_direction_min, wind_direction_max, wind_speed_min, wind_speed_max, beach_status, surf_status, last_update) VALUES (default, 1, 6, 'Pärnu Rand', 'Ilus ja hea surfirand. Surfilaenutus', 58.3721225, 24.5025777, 190, 270, 7.0, 20.0, 'A', null, '2025-09-23 12:29:06.012948');
INSERT INTO tuulest.beach (id, user_id, county_id, name, description, lat, lng, wind_direction_min, wind_direction_max, wind_speed_min, wind_speed_max, beach_status, surf_status, last_update) VALUES (default, 1, 1, 'Kakumäe Rand', 'Ilus rand', 59.4498421, 24.5755913, 280, 350, 7.0, 20.0, 'A', null, '2025-09-23 12:32:39.172368');


