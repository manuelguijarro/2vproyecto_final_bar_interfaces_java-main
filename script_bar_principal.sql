DROP DATABASE IF EXISTS bar_interface;
CREATE DATABASE bar_interface;

USE bar_interface;

DROP TABLE IF EXISTS Mesas;

CREATE TABLE Mesas (
    id_mesa INT AUTO_INCREMENT PRIMARY KEY,
    nombre_mesa VARCHAR(20) NOT NULL,
    total_gastado DECIMAL(10, 2) DEFAULT 0
);

DROP TABLE IF EXISTS Productos;

CREATE TABLE Productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(50) NOT NULL,
    categoria_producto VARCHAR(20) NOT NULL,
    precio_producto DECIMAL(10, 2) DEFAULT 0
);

DROP TABLE IF EXISTS Mesas_Actuales;

CREATE TABLE Mesas_Actuales (
    id_mesa_actual INT  PRIMARY KEY,
    nombre_mesa_actual VARCHAR(20) NOT NULL,
    total_gastado DECIMAL(10, 2) DEFAULT 0
);

DROP TABLE IF EXISTS Pedidos;
CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_mesa INT NOT NULL,
    id_producto INT NOT NULL,
    FOREIGN KEY (id_mesa) REFERENCES Mesas(id_mesa),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);

DROP TABLE IF EXISTS Pedidos_Actuales;
CREATE TABLE Pedidos_Actuales (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_mesa_actual INT NOT NULL,
    id_producto INT NOT NULL,
    FOREIGN KEY (id_mesa_actual) REFERENCES Mesas_Actuales(id_mesa_actual),
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto)
);
/*Refrescos*/
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Coca-Cola', 'Refrescos', 2.50);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Pepsi', 'Refrescos', 2.25);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Nestea', 'Refrescos', 3.00);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Fanta Naranja', 'Refrescos', 2.00);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Fanta Limon', 'Refrescos', 2.00);

/*Cafes*/
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Café con leche', 'Café', 1.20);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Café solo', 'Café', 1.20);

/*Cervezas*/
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Copa Cerveza', 'Cervezas', 3.50);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Tercio Estrella Galicia', 'Cervezas', 4.00);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Tercio Estrella Especial 1906', 'Cervezas', 4.50);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Tercio Cerveza Especial Voll Damm', 'Cervezas', 4.50);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Tercio Estrella Galicia Sin Alcohol Negra', 'Cervezas Sin Alcohol', 2.50);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Tercio Estrella Galicia Sin Alcohol Tostada', 'Cervezas Sin Alcohol', 2.50);


/*Comidas*/
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Racion Nachos Con Queso Y Salsa Guacamole', 'Comida', 8.00);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Racion Alitas De Pollo Y Salsa BQQ', 'Comida', 7.00);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Bocadillo Calamares Con Ali-Oli', 'Comida', 4.50);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Hamburguesa Completa ', 'Comida', 4.50);
INSERT INTO Productos (nombre_producto, categoria_producto, precio_producto) VALUES ('Sandwich Mixto', 'Comida', 2.50);


INSERT INTO Mesas_Actuales (id_mesa_actual, nombre_mesa_actual, total_gastado) VALUES (1, 'mesa_1', 0);
INSERT INTO Mesas_Actuales (id_mesa_actual, nombre_mesa_actual, total_gastado) VALUES (2, 'mesa_2', 0);
INSERT INTO Mesas_Actuales (id_mesa_actual, nombre_mesa_actual, total_gastado) VALUES (3, 'mesa_3', 0);
INSERT INTO Mesas_Actuales (id_mesa_actual, nombre_mesa_actual, total_gastado) VALUES (4, 'mesa_4', 0);
INSERT INTO Mesas_Actuales (id_mesa_actual, nombre_mesa_actual, total_gastado) VALUES (5, 'mesa_5', 0);
INSERT INTO Mesas_Actuales (id_mesa_actual, nombre_mesa_actual, total_gastado) VALUES (6, 'mesa_6', 0);
INSERT INTO Mesas_Actuales (id_mesa_actual, nombre_mesa_actual, total_gastado) VALUES (7, 'mesa_7', 0);
