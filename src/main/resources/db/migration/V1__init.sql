CREATE TABLE category
(
    id   uuid    NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT category_pk PRIMARY KEY (id)
);

CREATE TABLE param
(
    id               uuid    NOT NULL,
    name             VARCHAR NOT NULL,
    category_id      uuid    NOT NULL,
    unit_measurement VARCHAR,
    CONSTRAINT param_pk PRIMARY KEY (id),
    CONSTRAINT param_category_fk FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE product
(
    id               uuid    NOT NULL,
    name             VARCHAR NOT NULL,
    category_id      uuid    NOT NULL,
    price            money   NOT NULL,
    available_amount INT     NOT NULL,
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    CONSTRAINT product_pk PRIMARY KEY (id),
    CONSTRAINT product_category_fk FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE product_param
(
    product_id uuid    NOT NULL,
    param_id   uuid    NOT NULL,
    value      VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT param_product_param_fk FOREIGN KEY (param_id) REFERENCES param (id),
    CONSTRAINT product_product_param_fk FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE users
(
    id           uuid    NOT NULL,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    email        VARCHAR,
    phone_number VARCHAR,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id              uuid    NOT NULL,
    user_id         uuid    NOT NULL,
    delivary_method VARCHAR NOT NULL,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,
    CONSTRAINT orders_pk PRIMARY KEY (id),
    CONSTRAINT orders_users_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE order_product
(
    order_id   uuid NOT NULL,
    product_id uuid NOT NULL,
    amount     SMALLINT,
    CONSTRAINT orders_order_product_fk FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT product_order_product_fk FOREIGN KEY (product_id) REFERENCES product (id)
);