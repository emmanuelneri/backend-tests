CREATE TABLE cliente
(
    id        BIGSERIAL PRIMARY KEY,
    nome      VARCHAR(200) NOT NULL,
    documento VARCHAR(20) NOT NULL,
    CONSTRAINT pedido_uk UNIQUE(documento)
);