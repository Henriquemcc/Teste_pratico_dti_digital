create table rota (
    id bigint not null auto_increment,
    origem_x double not null,
    origem_y double not null,
    destino_x double not null,
    destino_y double not null,
    primary key(id)
);