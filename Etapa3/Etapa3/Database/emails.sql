create sequence id_emails start 1;

create table emails(
	id int default nextval('id_emails') primary key,
	id_remetente int references usuarios(id),
	id_destinatario int references usuarios(id),
	mensagem TEXT not null,
	dataEmail timestamp not null
);

insert into emails (id_remetente, id_destinatario, mensagem, dataEmail) values