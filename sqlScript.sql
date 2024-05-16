-- Insertion des barmakers
INSERT INTO barmaker (name, email, password) VALUES
                                                 ('John Doe', 'john@example.com', 'password123'),
                                                 ('Alice Smith', 'alice@example.com', 'securepassword'),
                                                 ('Michael Brown', 'michael@example.com', 'pass123'),
                                                 ('Emma Johnson', 'emma@example.com', 'emma456');

-- Insertion des catégories
INSERT INTO category (name, barmaker_id) VALUES
                                             ('Cocktails', 1),
                                             ('Mocktails', 1),
                                             ('Sodas', 2),
                                             ('Smoothies', 3),
                                             ('Shots', 4);

-- Insertion des cocktails
INSERT INTO cocktail (name, ingredient, price_s, price_m, price_l, barmaker_id) VALUES
                                                                                    ('Mojito', 'Rum, Mint, Lime, Soda Water', 8.99, 10.99, 12.99, 1),
                                                                                    ('Cosmopolitan', 'Vodka, Triple Sec, Cranberry Juice, Lime Juice', 9.99, 11.99, 13.99, 1),
                                                                                    ('Virgin Pina Colada', 'Pineapple Juice, Coconut Cream', 6.99, 8.99, 10.99, 2),
                                                                                    ('Strawberry Daiquiri', 'Rum, Lime Juice, Simple Syrup, Strawberries', 9.99, 11.99, 13.99, 2),
                                                                                    ('Mango Tango', 'Mango, Orange Juice, Yogurt, Honey', 7.99, 9.99, 11.99, 3),
                                                                                    ('Tequila Sunrise', 'Tequila, Orange Juice, Grenadine', 8.99, 10.99, 12.99, 4);

-- Insertion des commandes
INSERT INTO orders (status, total_price, order_at, barmaker_id) VALUES
                                                                    ('COMPLETED', 45.97, '2024-05-16 12:00:00', 1),
                                                                    ('PENDING', 12.99, '2024-05-16 10:30:00', 2),
                                                                    ('COMPLETED', 21.98, '2024-05-15 15:45:00', 3),
                                                                    ('PENDING', 9.99, '2024-05-15 18:20:00', 4);

-- Insertion des associations cocktail-category
INSERT INTO cocktail_category (cocktail_id, category_id) VALUES
                                                             (1, 1),
                                                             (2, 1),
                                                             (3, 2),
                                                             (4, 2),
                                                             (5, 4),
                                                             (6, 4);

-- Insertion des commandes de cocktails
INSERT INTO cocktail_order (status, size, price, cocktail_id, order_id) VALUES
                                                                            ('COMPLETED', 'MEDIUM', 10.99, 1, 1),
                                                                            ('COMPLETED', 'LARGE', 13.99, 2, 1),
                                                                            ('PENDING', 'SMALL', 6.99, 3, 2),
                                                                            ('COMPLETED', 'MEDIUM', 11.99, 4, 3),
                                                                            ('PENDING', 'LARGE', 9.99, 5, 4),
                                                                            ('COMPLETED', 'SMALL', 10.99, 6, 4);
