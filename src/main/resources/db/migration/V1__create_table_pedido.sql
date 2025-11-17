create table pedido (
    id bigint not null auto_increment,
    nome varchar(100),
    valor float,
    descricao varchar(500),
    peso float not null,
    prioridade varchar(6) not null,
    x double not null,
    y double not null,
    primary key(id)
);