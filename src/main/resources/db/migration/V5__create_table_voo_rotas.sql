create table voo_rotas (
    voo_id bigint not null,
    rota_id bigint not null,
    primary key (voo_id, rota_id),
    constraint fk_voo foreign key (voo_id) references voo(id),
    constraint fk_rota foreign key (rota_id) references rota(id)
);