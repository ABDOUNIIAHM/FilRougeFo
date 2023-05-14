USE filrouge_bdd;
INSERT INTO Months(id, name) VALUES
                                 (1,'January'),
                                 (2,'February'),
                                 (3,'March'),
                                 (4,'April'),
                                 (5,'May'),
                                 (6,'June'),
                                 (7,'July'),
                                 (8,'August'),
                                 (9,'September'),
                                 (10,'October'),
                                 (11,'November'),
                                 (12,'December');

INSERT INTO Admins(id, username, isSuperAdmin, password) VALUES
                                                             (1,'superadmin', 1, 'superadmin'),
                                                             (2,'admin', 0, 'admin');

INSERT INTO Categories(id, name) VALUES
                                     (1, 'Légume-feuille'),
                                     (2, 'Légume-fleur'),
                                     (3, 'Légume-racine'),
                                     (4, 'Légume-bulbe'),
                                     (5, 'Légume-fruit');

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (1, 'Carottes', 'kg', 1.89, 0.2,
     'Un légume plein de bêta-carotènes.',
     12.00, 3, '/resources/img/carrots.jpg');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (1, 7),
                                                   (1, 8),
                                                   (1, 9),
                                                   (1, 10);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (2, 'Tomates Cerises', 'kg', 4.55, 0.2,
     'Les tomates sont un en réalité un fruit.\nLes tomates cerises sont de toutes petites tomates',
     15.550, 5, '/resources/img/cherry-tomatoes.jpg');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (2, 6),
                                                   (2, 7),
                                                   (2, 8),
                                                   (2, 9);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (3, 'Avocats Hass', 'piece', 1.80, 0.2,
     'Un fruit cultivé dans des régions chaudes.',
     30, 5, '/resources/img/avocadoes.jpg');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (3, 1),
                                                   (3, 2),
                                                   (3, 3),
                                                   (3, 4),
                                                   (3, 5);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (4, 'Epinards', 'kg', 2.56, 0.2,
     'Riche en fer d\'après la culture populaire',
     15.550, 1, '/resources/img/spinash.jpg');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (4, 12),
                                                   (4, 1),
                                                   (4, 2),
                                                   (4, 3),
                                                   (4, 4),
                                                   (4, 5),
                                                   (4, 6);

INSERT INTO OrderStatuses(id, name) VALUES
 (1, 'PENDING'), # = basket
 (2, 'VALIDATED'),
 (3, 'SHIPPED'),
 (4, 'CANCELED');

INSERT INTO PaymentMethods(id, name) VALUES
 (1, 'CREDIT CARD'),
 (2, 'PayPal');
