create table entrega (
    id bigint not null auto_increment,
    drone_id bigint,
    deposito_id bigint,
    voo_id bigint,

    primary key (id),
    constraint fk_entrega_drone foreign key (drone_id) references drone(id) on delete set null,
    constraint fk_entrega_deposito foreign key (deposito_id) references deposito(id) on delete set null,
    constraint fk_entrega_voo foreign key (voo_id) references voo(id) on delete set null
);