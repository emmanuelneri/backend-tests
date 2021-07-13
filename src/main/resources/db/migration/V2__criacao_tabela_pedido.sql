CREATE TABLE pedido
(
    id         BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    data_hora  TIMESTAMP NOT NULL DEFAULT now(),
    total      NUMERIC(10,2) NOT NULL,
    status     VARCHAR(50) NOT NULL,
    CONSTRAINT pedido_cliente_id_fk FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);