
INSERT INTO CLIENTE(nome, palpite, data_criacao) VALUES('Aluno', '2,10,18,20,25,30,34,35,21', '2019-05-05 18:00:00');
INSERT INTO CLIENTE(nome, palpite, data_criacao) VALUES('Ana', '2,10,18,20,25,30,34,35,21', '2019-05-05 18:00:00');
INSERT INTO CLIENTE(nome, palpite, data_criacao) VALUES('Maria', '2,10,18,20,25,30,34,35,21', '2019-05-05 18:00:00');

INSERT INTO USUARIO(nome, senha) VALUES('Maria', '$2a$10$MDtldAj7MRBCpJrwpXJLmeobRg8ZHV5Imp7gtzXa.6chYCf2tK4B2');
INSERT INTO USUARIO(nome, senha) VALUES('Joao', '$2a$10$MDtldAj7MRBCpJrwpXJLmeobRg8ZHV5Imp7gtzXa.6chYCf2tK4B2');

INSERT INTO PERFIL(id, nome) VALUES(1,'ROLE_MODERADOR');
INSERT INTO PERFIL(id, nome) VALUES(2,'ROLE_USER');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1,1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(2,2);

INSERT INTO CURSO(nome, categoria) VALUES('Filosofia','humanas');