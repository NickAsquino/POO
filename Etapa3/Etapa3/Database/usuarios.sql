create sequence id_usuarios start 1;

create table usuarios(
	id int default nextval('id_usuarios') primary key,
	nome varchar(50) not null,
	email varchar(50) unique not null,
	senha varchar(50) not null
);

create sequence id_usuarios;

insert into usuarios (nome, email, senha) values