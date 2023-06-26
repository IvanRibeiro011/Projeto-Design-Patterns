INSERT INTO tb_livro(NOME,DESCRICAO, AUTOR,QUANTIDADE,DISPONIVEL) VALUES ('Livro 1', 'Descrição', 'Autor',1,TRUE);
INSERT INTO tb_livro(NOME, DESCRICAO, AUTOR,QUANTIDADE,DISPONIVEL) VALUES ('Livro 2', 'Descrição', 'Autor',10,TRUE);
INSERT INTO tb_livro(NOME, DESCRICAO, AUTOR,QUANTIDADE,DISPONIVEL) VALUES ('Livro 3', 'Descrição', 'Autor',10,TRUE);

INSERT INTO tb_usuario(MATRICULA,EMAIL,NOME,PASSWORD) VALUES ('123456', 'ivanildo@gmail.com','Ivanildo','$2a$10$ub4j6asietr0JhKrOvMuv.828MXUcxGly1tI5zzKGFUih1zOkCx6a');
INSERT INTO tb_usuario(MATRICULA,EMAIL,NOME,PASSWORD) VALUES ('123457','vinicius@gmail.com', 'Vinicius','$2a$10$ub4j6asietr0JhKrOvMuv.828MXUcxGly1tI5zzKGFUih1zOkCx6a');
INSERT INTO tb_usuario(MATRICULA,EMAIL,NOME,PASSWORD) VALUES ('123458', 'lucas@gmail.com','Lucas','$2a$10$ub4j6asietr0JhKrOvMuv.828MXUcxGly1tI5zzKGFUih1zOkCx6a');

INSERT INTO tb_role (authority) VALUES ('ROLE_ALUNO');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 1);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (1, 2);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (2, 2);
INSERT INTO tb_usuario_role (usuario_id, role_id) VALUES (3, 1);
-- INSERT INTO tb_aluguel (aluno_id,data_aluguel,data_estipulada,data_devolucao) VALUES (1,NOW(),select dateadd(d,7,current_timestamp),select dateadd(d,8,current_timestamp));
-- INSERT INTO tb_aluguel (aluno_id,data_aluguel,data_estipulada,data_devolucao) VALUES (2,Now(),select dateadd(d,7,current_timestamp),select dateadd(d,8,current_timestamp));
-- INSERT INTO tb_aluguel (aluno_id,data_aluguel,data_estipulada,data_devolucao) VALUES (3,Now(),select dateadd(d,7,current_timestamp),select dateadd(d,8,current_timestamp));

-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (1,2);
-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (1,1);
-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (2,3);
-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (3,2);
--


