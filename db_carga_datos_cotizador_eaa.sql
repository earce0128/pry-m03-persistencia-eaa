USE cotizador_eaa;

-- Borrado opcional de datos existentes (para ejecuciones repetidas)
-- Descomentar si es necesario y ejecutar con precaución
-- SET FOREIGN_KEY_CHECKS = 0;
-- DELETE FROM detalle_cotizacion;
-- DELETE FROM sub_componente_pc;
-- DELETE FROM componente;

-- Insertar Componentes (Monitores)
INSERT INTO componente (idComponente, categoria, descripcion, memoria, capAlmacenamiento, costo, precioBase, marca, modelo) VALUES
('MON-0001', 'Monitor', 'Monitor LED 24 pulgadas', NULL, NULL, 150.00, 250.00, 'Samsung', 'S24F350'),
('MON-0002', 'Monitor', 'Monitor IPS 27 pulgadas', NULL, NULL, 220.00, 350.00, 'LG', '27MK400H'),
('MON-0003', 'Monitor', 'Monitor Curvo 32 pulgadas', NULL, NULL, 350.00, 500.00, 'Acer', 'ED320QR'),
('MON-0004', 'Monitor', 'Monitor 4K 27 pulgadas', NULL, NULL, 300.00, 450.00, 'Dell', 'S2721QS'),
('MON-0005', 'Monitor', 'Monitor Ultrawide 34 pulgadas', NULL, NULL, 450.00, 650.00, 'Xiaomi', 'Mi Curved 34');

-- Insertar Componentes (Tarjetas de Video)
INSERT INTO componente (idComponente, categoria, descripcion, memoria, capAlmacenamiento, costo, precioBase, marca, modelo) VALUES
('TVID-0001', 'Tarjeta de Video', 'NVIDIA GeForce RTX 3060 12GB', '12GB GDDR6', NULL, 300.00, 450.00, 'NVIDIA', 'RTX 3060'),
('TVID-0002', 'Tarjeta de Video', 'AMD Radeon RX 6700 XT 12GB', '12GB GDDR6', NULL, 350.00, 520.00, 'AMD', 'RX 6700 XT'),
('TVID-0003', 'Tarjeta de Video', 'NVIDIA GeForce RTX 3070 8GB', '8GB GDDR6', NULL, 450.00, 680.00, 'ASUS', 'ROG Strix RTX 3070'),
('TVID-0004', 'Tarjeta de Video', 'AMD Radeon RX 6600 8GB', '8GB GDDR6', NULL, 250.00, 380.00, 'MSI', 'RX 6600 MECH 2X'),
('TVID-0005', 'Tarjeta de Video', 'NVIDIA GeForce RTX 3050 8GB', '8GB GDDR6', NULL, 200.00, 300.00, 'Gigabyte', 'RTX 3050 Eagle');

-- Insertar Componentes (Discos Duros)
INSERT INTO componente (idComponente, categoria, descripcion, memoria, capAlmacenamiento, costo, precioBase, marca, modelo) VALUES
('DD-0001', 'Disco Duro', 'SSD 500GB NVMe', NULL, '500GB', 60.00, 100.00, 'Samsung', '970 EVO Plus 500GB'),
('DD-0002', 'Disco Duro', 'SSD 1TB SATA', NULL, '1TB', 80.00, 130.00, 'Crucial', 'MX500 1TB'),
('DD-0003', 'Disco Duro', 'HDD 2TB 7200RPM', NULL, '2TB', 50.00, 80.00, 'Seagate', 'Barracuda 2TB'),
('DD-0004', 'Disco Duro', 'SSD 250GB NVMe', NULL, '250GB', 40.00, 70.00, 'Western Digital', 'WD Blue SN570 250GB'),
('DD-0005', 'Disco Duro', 'SSD 1TB NVMe Gen4', NULL, '1TB', 120.00, 180.00, 'Kingston', 'KC3000 1TB');

-- Insertar Componentes (PCs)
INSERT INTO componente (idComponente, categoria, descripcion, memoria, capAlmacenamiento, costo, precioBase, marca, modelo) VALUES
('PC-0001', 'PC', 'PC Gamer Ryzen 5, RTX 3060', NULL, NULL, 800.00, 1200.00, 'Armada', 'Gamer Pro'),
('PC-0002', 'PC', 'PC Oficina Intel i5, Integrada', NULL, NULL, 500.00, 800.00, 'OfficeMax', 'Essential'),
('PC-0003', 'PC', 'Workstation Intel Xeon, Quadro', NULL, NULL, 1500.00, 2500.00, 'PowerTech', 'Workstation X'),
('PC-0004', 'PC', 'PC Media Center AMD Ryzen 3, Integrada', NULL, NULL, 400.00, 650.00, 'HomeSys', 'Media Hub'),
('PC-0005', 'PC', 'PC Edición Intel i7, RTX 3070', NULL, NULL, 1200.00, 1800.00, 'Creative', 'Edit Pro');

-- Insertar SubComponentes para PC-0001
INSERT INTO sub_componente_pc (idPC, idSubComponente, cantidad) VALUES
('PC-0001', 'MON-0001', 1),
('PC-0001', 'DD-0001', 1),
('PC-0001', 'TVID-0001', 1);

-- Insertar SubComponentes para PC-0002
INSERT INTO sub_componente_pc (idPC, idSubComponente, cantidad) VALUES
('PC-0002', 'MON-0002', 1),
('PC-0002', 'DD-0002', 1);

-- Insertar SubComponentes para PC-0003
INSERT INTO sub_componente_pc (idPC, idSubComponente, cantidad) VALUES
('PC-0003', 'MON-0003', 1),
('PC-0003', 'DD-0003', 2),
('PC-0003', 'TVID-0003', 1);

-- Insertar SubComponentes para PC-0004
INSERT INTO sub_componente_pc (idPC, idSubComponente, cantidad) VALUES
('PC-0004', 'MON-0004', 1),
('PC-0004', 'DD-0004', 1);

-- Insertar SubComponentes para PC-0005
INSERT INTO sub_componente_pc (idPC, idSubComponente, cantidad) VALUES
('PC-0005', 'MON-0005', 1),
('PC-0005', 'DD-0005', 1),
('PC-0005', 'TVID-0005', 1);

-- Insertar Cotizaciones
INSERT INTO cotizacion (fechaCotizacion, total) VALUES
('2025-05-09', 1450.00),
('2025-05-10', 930.00),
('2025-05-11', 2880.00),
('2025-05-12', 720.00),
('2025-05-13', 2050.00),
('2025-05-14', 600.00),
('2025-05-15', 1350.00),
('2025-05-16', 1080.00),
('2025-05-17', 550.00),
('2025-05-18', 1900.00);

-- Insertar Detalles de Cotización (Cotización 1)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(1, 'PC-0001', 1, 'PC', 'PC Gamer Ryzen 5, RTX 3060', 1200.00, 1200.00),
(1, 'MON-0001', 1, 'Monitor', 'Monitor LED 24 pulgadas', 250.00, 250.00);

-- Insertar Detalles de Cotización (Cotización 2)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(2, 'PC-0002', 1, 'PC', 'PC Oficina Intel i5, Integrada', 800.00, 800.00),
(2, 'DD-0002', 1, 'Disco Duro', 'SSD 1TB SATA', 130.00, 130.00);

-- Insertar Detalles de Cotización (Cotización 3)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(3, 'PC-0003', 1, 'PC', 'Workstation Intel Xeon, Quadro', 2500.00, 2500.00),
(3, 'MON-0003', 1, 'Monitor', 'Monitor Curvo 32 pulgadas', 500.00, 500.00),
(3, 'DD-0003', 1, 'Disco Duro', 'HDD 2TB 7200RPM', 80.00, 80.00);

-- Insertar Detalles de Cotización (Cotización 4)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(4, 'PC-0004', 1, 'PC', 'PC Media Center AMD Ryzen 3, Integrada', 650.00, 650.00),
(4, 'MON-0004', 1, 'Monitor', 'Monitor 4K 27 pulgadas', 450.00, 450.00),
(4, 'DD-0004', 1, 'Disco Duro', 'SSD 250GB NVMe', 70.00, 70.00);

-- Insertar Detalles de Cotización (Cotización 5)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(5, 'PC-0005', 1, 'PC', 'PC Edición Intel i7, RTX 3070', 1800.00, 1800.00),
(5, 'TVID-0005', 1, 'Tarjeta de Video', 'NVIDIA GeForce RTX 3050 8GB', 300.00, 300.00);

-- Insertar Detalles de Cotización (Cotización 6)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(6, 'MON-0001', 2, 'Monitor', 'Monitor LED 24 pulgadas', 500.00, 250.00),
(6, 'DD-0001', 1, 'Disco Duro', 'SSD 500GB NVMe', 100.00, 100.00);

-- Insertar Detalles de Cotización (Cotización 7)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(7, 'TVID-0002', 1, 'Tarjeta de Video', 'AMD Radeon RX 6700 XT 12GB', 520.00, 520.00),
(7, 'DD-0003', 1, 'Disco Duro', 'HDD 2TB 7200RPM', 80.00, 80.00),
(7, 'MON-0002', 1, 'Monitor', 'Monitor IPS 27 pulgadas', 350.00, 350.00);

-- Insertar Detalles de Cotización (Cotización 8)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(8, 'MON-0005', 1, 'Monitor', 'Monitor Ultrawide 34 pulgadas', 650.00, 650.00),
(8, 'DD-0005', 1, 'Disco Duro', 'SSD 1TB NVMe Gen4', 180.00, 180.00),
(8, 'TVID-0004', 1, 'Tarjeta de Video', 'AMD Radeon RX 6600 8GB', 380.00, 380.00);

-- Insertar Detalles de Cotización (Cotización 9)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(9, 'DD-0002', 2, 'Disco Duro', 'SSD 1TB SATA', 260.00, 130.00),
(9, 'MON-0004', 1, 'Monitor', 'Monitor 4K 27 pulgadas', 450.00, 450.00);

-- Insertar Detalles de Cotización (Cotización 10)
INSERT INTO det_cotizacion (idCotizacion, idComponente, cantidad, categoria, descripcion, importeCotizado, precioBase) VALUES
(10, 'TVID-0003', 1, 'Tarjeta de Video', 'NVIDIA GeForce RTX 3070 8GB', 680.00, 680.00),
(10, 'DD-0001', 1, 'Disco Duro', 'SSD 500GB NVMe', 100.00, 100.00),
(10, 'MON-0003', 1, 'Monitor', 'Monitor Curvo 32 pulgadas', 500.00, 500.00),
(10, 'PC-0002', 1, 'PC', 'PC Oficina Intel i5, Integrada', 800.00, 800.00);