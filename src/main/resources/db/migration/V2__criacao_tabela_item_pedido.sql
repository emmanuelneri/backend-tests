CREATE TABLE item_pedido
(
    id         BIGSERIAL PRIMARY KEY,
    pedido_id  BIGINT NOT NULL,
    quantidade INT NOT NULL,
    valor      NUMERIC(10,2) NOT NULL,
    total      NUMERIC(10,2) NOT NULL,
    CONSTRAINT item_pedido_pedido_id_fk FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);