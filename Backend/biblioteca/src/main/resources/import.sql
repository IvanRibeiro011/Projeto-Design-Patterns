INSERT INTO tb_livro(NOME, DESCRICAO, AUTOR,QUANTIDADE,DISPONIVEL) VALUES ('Livro 1', 'Descrição', 'Autor',1,TRUE);
INSERT INTO tb_livro(NOME, DESCRICAO, AUTOR,QUANTIDADE,DISPONIVEL) VALUES ('Livro 2', 'Descrição', 'Autor',10,TRUE);
INSERT INTO tb_livro(NOME, DESCRICAO, AUTOR,QUANTIDADE,DISPONIVEL) VALUES ('Livro 3', 'Descrição', 'Autor',10,TRUE);

INSERT INTO tb_usuario(MATRICULA, NOME) VALUES ('123456', 'Ivanildo');
INSERT INTO tb_usuario(MATRICULA, NOME) VALUES ('123457', 'Vinicius');
INSERT INTO tb_usuario(MATRICULA, NOME) VALUES ('123458', 'Lucas');
--
-- INSERT INTO tb_aluguel (aluno_id,data_aluguel,data_estipulada,data_devolucao) VALUES (1,NOW(),select dateadd(d,7,current_timestamp),select dateadd(d,8,current_timestamp));
-- INSERT INTO tb_aluguel (aluno_id,data_aluguel,data_estipulada,data_devolucao) VALUES (2,Now(),select dateadd(d,7,current_timestamp),select dateadd(d,8,current_timestamp));
-- INSERT INTO tb_aluguel (aluno_id,data_aluguel,data_estipulada,data_devolucao) VALUES (3,Now(),select dateadd(d,7,current_timestamp),select dateadd(d,8,current_timestamp));

-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (1,2);
-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (1,1);
-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (2,3);
-- INSERT INTO tb_aluguel_livro(aluguel_id,livro_id) VALUES (3,2);
--


