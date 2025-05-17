DROP DATABASE IF EXISTS cotizador_eaa;
CREATE DATABASE cotizador_eaa;
USE cotizador_eaa;

CREATE TABLE promocion (
    numPromocion BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    descripcion TEXT,
    fechVigenciaDesde DATE NOT NULL,
    fechVigenciaHasta DATE
);

CREATE TABLE det_promocion (
    numDetPromocion BIGINT UNSIGNED AUTO_INCREMENT,
    numPromocion BIGINT UNSIGNED,
    nombre VARCHAR(50),
    descripcion TEXT,
    esBase BOOLEAN,
    llevarN INT UNSIGNED,
    pagueM INT UNSIGNED,
    porcDsctoPlan DECIMAL(5, 2),
    tipoPromAcumulable VARCHAR(50),
    tipoPromBase VARCHAR(50),
    PRIMARY KEY (numDetPromocion, numPromocion),
    FOREIGN KEY (numPromocion) REFERENCES promocion(numPromocion) ON DELETE CASCADE,
    CONSTRAINT uc_nombre UNIQUE (nombre)
);

CREATE TABLE det_prom_dscto_x_cant (
    numPromDsctoCant BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    numDetPromocion BIGINT UNSIGNED,
    numPromocion BIGINT UNSIGNED,
    cantidad INT UNSIGNED,
    dscto DECIMAL(5, 2),
    FOREIGN KEY (numDetPromocion, numPromocion) REFERENCES det_promocion(numDetPromocion, numPromocion) ON DELETE CASCADE,
    CONSTRAINT uc_numPromCant_numDetProm_numProm UNIQUE (numPromDsctoCant, numDetPromocion, numPromocion)
);

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
    numPromocion BIGINT UNSIGNED,
    FOREIGN KEY (numPromocion) REFERENCES promocion(numPromocion) ON DELETE SET NULL,
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