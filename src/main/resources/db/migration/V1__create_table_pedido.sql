create table pedido (
    id bigint not null auto_increment,
    peso float not null,
    prioridade varchar(6) not null,
    x double not null,
    y double not null,
    primary key(id)
);