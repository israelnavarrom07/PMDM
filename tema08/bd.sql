CREATE DATABASE IF NOT EXISTS pmdm;
USE pmdm;

CREATE TABLE freestylers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    resultado VARCHAR(100) NOT NULL,
    imagen VARCHAR(255) NOT NULL
);

INSERT INTO freestylers (nombre, resultado, imagen) VALUES
('Wos', 'Campeón · Temporada 1 (2018)', 'https://picsum.photos/id/10/400/400'),
('Trueno', 'Campeón · Temporada 2 (2019)', 'https://picsum.photos/id/11/400/400'),
('Papo', 'Subcampeón · Temporada 2 (2019)', 'https://picsum.photos/id/12/400/400'),
('Stuart', 'Campeón · Temporada 3 (2020/21)', 'https://picsum.photos/id/13/400/400'),
('Papo', 'Subcampeón · Temporada 3 (2020/21)', 'https://picsum.photos/id/14/400/400'),
('Papo', 'Campeón · Temporada 4 (2022/23)', 'https://picsum.photos/id/15/400/400'),
('Mecha', 'Subcampeón · Temporada 4 (2022/23)', 'https://picsum.photos/id/16/400/400'),
('Larrix', 'Campeón · Temporada 5 (2023)', 'https://picsum.photos/id/17/400/400');