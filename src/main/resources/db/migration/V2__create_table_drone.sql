create table drone (
    id bigint not null auto_increment,
    marca varchar(100),
    modelo varchar(100),
    numero_serie varchar(100),
    distancia_por_carga float not null,
    capacidade float not null,
    primary key(id)
);