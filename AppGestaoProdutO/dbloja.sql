CREATE DATABASE IF NOT EXISTS dbloja;

USE dbloja;

CREATE TABLE IF NOT EXISTS categorias (
    id   INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

-- Inserindo algumas categorias de exemplo
INSERT INTO categorias (nome) VALUES
    ('Eletrônicos'),
    ('Roupas'),
    ('Alimentos'),
    ('Livros'),
    ('Esportes');