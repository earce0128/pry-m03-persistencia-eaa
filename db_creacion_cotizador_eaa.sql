DROP DATABASE IF EXISTS cotizador_eaa;
CREATE DATABASE cotizador_eaa;
USE cotizador_eaa;

CREATE TABLE componente (
    idComponente VARCHAR(15) PRIMARY KEY,
    categoria VARCHAR(50),
    descripcion VARCHAR(255),
    memoria VARCHAR(50),
    capAlmacenamiento VARCHAR(50),
    costo DECIMAL(10, 2),
    precioBase DECIMAL(10, 2),
    marca VARCHAR(50),
    modelo VARCHAR(50),
    CONSTRAINT uc_marca_modelo UNIQUE (marca, modelo)
);

CREATE TABLE cotizacion (
    idCotizacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    fechaCotizacion DATE NOT NULL,
    total DECIMAL(10, 2)
);

CREATE TABLE det_cotizacion (
    idCotizacion BIGINT NOT NULL,
    idComponente VARCHAR(15) NOT NULL,
    cantidad INT NOT NULL,
    categoria VARCHAR(50),
    descripcion VARCHAR(255),
    importeCotizado DECIMAL(10, 2),
    precioBase DECIMAL(10, 2),
    PRIMARY KEY (idCotizacion, idComponente),
    FOREIGN KEY (idCotizacion) REFERENCES cotizacion(idCotizacion),
    FOREIGN KEY (idComponente) REFERENCES componente(idComponente)
);

CREATE TABLE sub_componente_pc (
    idPC VARCHAR(15),
    idSubComponente VARCHAR(15),
    cantidad INT NOT NULL,
    PRIMARY KEY (idPC, idSubComponente),
    FOREIGN KEY (idPC) REFERENCES componente(idComponente),
    FOREIGN KEY (idSubComponente) REFERENCES componente(idComponente)
);