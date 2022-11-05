INSERT INTO destination(id, name, country) VALUES
    (1, 'Budapest', 'Hungary'),
    (2, 'Veszprém', 'Hungary');

INSERT INTO attraction(id, destination_id, name, description, category) VALUES
    (10, 1, 'Buda Castle', 'Buda Castle is the historical castle and palace complex of the Hungarian Kings in Budapest.', 'HISTORY'),
    (11, 2, 'Zoo', 'Zoo is located in a beautiful environment, not far from lake Balaton. Interesting animals, feeding actions.', 'ENTERTAINMENT');

INSERT INTO users(id, full_name, role, login_name, password) VALUES
    (100, 'Péter Kovács', 'USER', 'pkovacs', 'pk-secret'),
    (101, 'Sándor Nagy', 'ADMIN', 'snagy', 'sn-secret');

INSERT INTO trip(id, user_id, destination_id, start_date, end_date) VALUES
    (1, 100, 1, '2022-08-15', '2022-08-17'),
    (2, 100, 2, '2022-08-20', '2022-08-21');

INSERT INTO visit(id, attraction_id, visit_date, trip_id) VALUES
    (20, 10, '2022-08-15', 1),
    (21, 11, '2022-08-20', 2);

INSERT INTO review(id, rating, comment, user_id, attraction_id) VALUES
    (1, 5, 'Amazing building, unique shapes.', 100, 11);
