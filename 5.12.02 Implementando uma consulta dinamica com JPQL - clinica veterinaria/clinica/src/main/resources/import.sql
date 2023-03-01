insert into endereco (id, rua, numero, bairro, cidade, estado) values (1, 'BBB', 12, 'Zaira', 'Maua', 'RJ');

insert into cuidador (id, nome, idade, telefone, endereco_id) values (1, 'Bruno Afonso', 28, '11944445555', 1);
insert into cuidador (id, nome, idade, telefone, endereco_id) values (2, 'Jeferson Ribeiro', 30, '11977778888', 1);
insert into cuidador (id, nome, idade, telefone, endereco_id) values (3, 'Viviane', 51, '11922223333', 1);

insert into animal_domestico (id, nome, classificacao, idade, genero, cuidador_id) values (1, 'Tico', 'Gato', 6, 'Macho', 1);
insert into animal_domestico (id, nome, classificacao, idade, genero, cuidador_id) values (2, 'Maggie', 'Cachorro', 6, 'Femea', 2);
insert into animal_domestico (id, nome, classificacao, idade, genero, cuidador_id) values (3, 'Tripa', 'Gato', 4, 'Femea', 3);

