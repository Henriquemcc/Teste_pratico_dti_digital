create table entrega_pedidos (
    entrega_id bigint not null,
    pedidos_id bigint not null,

    primary key (entrega_id, pedidos_id),

    constraint fk_entrega_pedidos_entrega foreign key (entrega_id) references entrega(id) on delete cascade,
    constraint fk_entrega_pedidos_pedido foreign key (pedidos_id) references pedido(id) on delete cascade
);