INSERT INTO roles (id_rol, nombre, descripcion) VALUES (1, 'ADMIN', 'Administrador');
INSERT INTO roles (id_rol, nombre, descripcion) VALUES (2, 'USER', 'Usuario');
INSERT INTO roles (id_rol, nombre, descripcion) VALUES (3, 'TEST', 'TEST');

INSERT INTO usuarios (id_usuario, username, password) VALUES (1, 'usertest1', '$2a$10$SIrULYPjlPzp89HdEWY3quHmxnzLNqyvWixUhArWlITrY6LUo64Vi');
INSERT INTO usuarios (id_usuario, username, password) VALUES (2, 'usertest2', '$2a$10$/3bze5NAenNlNV.Kt5issuUF7qTFQBQHtVNFPQeEX9Og8TZo15OZS');

INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (1, 1);
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (1, 2);
INSERT INTO usuario_rol (id_usuario, id_rol) VALUES (2, 3);