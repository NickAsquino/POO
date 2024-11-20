create table emails_excluidos (
    id_usuario INT references usuarios(id),
    id_email INT references emails(id),
    primary key (id_usuario, id_email)
);

insert into emails_excluidos (id_usuario, id_email) values