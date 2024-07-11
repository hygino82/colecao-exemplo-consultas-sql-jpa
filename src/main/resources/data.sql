CREATE table if not exists autor(
cod_autor int not null auto_increment primary key,
nome_autor varchar(50) not null
);
CREATE TABLE IF NOT EXISTS livro (
    cod_livro INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo_livro VARCHAR(100) NOT NULL,
    cod_autor INT not null,
    FOREIGN KEY (cod_autor) REFERENCES autor(cod_autor)
);


INSERT INTO autor(nome_autor) VALUES ('Machado de Assis');
INSERT INTO autor(nome_autor) VALUES ('Guimarães Rosa');
INSERT INTO autor(nome_autor) VALUES ('José de Alencar');
INSERT INTO autor(nome_autor) VALUES ('Paulo Coelho');
INSERT INTO autor(nome_autor) VALUES ('Jõao Cabral de Melo Neto');
INSERT INTO autor(nome_autor) VALUES ('Leandro Karnal');

INSERT INTO livro(titulo_livro, cod_autor) VALUES ('Memórias Póstumas de Brás Cubas', 1);
INSERT INTO livro(titulo_livro, cod_autor) VALUES ('Dom Casmuro', 1);
INSERT INTO livro(titulo_livro, cod_autor) VALUES ('Grande Sertão: Veredas', 2);
INSERT INTO livro(titulo_livro, cod_autor) VALUES ('A Moreninha', 3);
INSERT INTO livro(titulo_livro, cod_autor) VALUES ('O Diário de um Mago', 4);
INSERT INTO livro(titulo_livro, cod_autor) VALUES ('Morte e Vida Severina', 5);
INSERT INTO livro(titulo_livro, cod_autor) VALUES ('O Dilema do Porco Espinho', 6);
